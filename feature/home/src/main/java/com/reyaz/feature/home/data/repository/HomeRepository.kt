package com.reyaz.feature.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reyaz.core.common.Resource
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.StockEntity
import com.reyaz.core.network.data.paging.StockRemoteMediator
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.api.OverviewApiService
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPagedStocks(query: String): Flow<PagingData<StockEntity>>
}

private const val TAG = "HOME_REPOSITORY_IMPL"

class HomeRepositoryImpl(
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService,
    private val db: GrowwDatabase
) :
    HomeRepository {

    /*override suspend fun fetchTopGainerLoser(): Flow<Resource<Unit>> = flow {
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
    }*/

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedStocks(query: String): Flow<PagingData<StockEntity>> {
        val pagingSourceFactory = { db.growwDao().pagingSource() }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = StockRemoteMediator(
                db,
                alphaVantageApiService,
                overviewApiService,
                query
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}