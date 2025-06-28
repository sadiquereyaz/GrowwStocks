package com.reyaz.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.reyaz.core.common.Resource
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.StockEntity
import com.reyaz.core.network.data.paging.StocksRemoteRepository
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    //    fun getPagedStocks(query: String): Flow<PagingData<StockEntity>>
    fun getPagedStocks(): Pager<Int, StockEntity>
    suspend fun refreshStocks(): Flow<Resource<Unit>>
    suspend fun getTopGainerLoser(stockType: StockType): Flow<List<StockEntity>>
}

private const val TAG = "HOME_REPOSITORY_IMPL"

/*class HomeRepositoryImpl(
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService,
    private val db: GrowwDatabase
) :
    HomeRepository {

    override suspend fun fetchTopGainerLoser(): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading(message = "Loading top gainers and losers"))
            val response = alphaVantageApiService.fetchTopGainersLosers()
            if (response.isSuccessful) {
                response.body()?.let {

                    emit(Resource.Success())
                } ?:  throw Exception("Empty response")
            } else {
                throw Exception("Error occurred while fetching")
            }
        } catch (e: Exception) {
            Log.e(TAG, "fetchTopGainerLoser: ", e)
            emit(Resource.Error(e.message))
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedStocks(query: String): Flow<PagingData<StockEntity>> {
        val pagingSourceFactory = { db.growwDao().pagingSource() }

        return Pager(
            config = PagingConfig(pageSize =20),
            remoteMediator = StockRemoteMediator(
                db,
                alphaVantageApiService,
                overviewApiService,
                query
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}*/

class HomeRepositoryImpl(
    private val db: GrowwDatabase,
    private val stocksRemoteRepository: StocksRemoteRepository
) : HomeRepository {

    override fun getPagedStocks(): Pager<Int, StockEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { db.growwDao().pagingSource() }
        )
    }

    override suspend fun refreshStocks(): Flow<Resource<Unit>> =
        stocksRemoteRepository.refreshStocks()

    override suspend fun getTopGainerLoser(stockType: StockType): Flow<List<StockEntity>> = stocksRemoteRepository.fetchTop(stockType)

}
