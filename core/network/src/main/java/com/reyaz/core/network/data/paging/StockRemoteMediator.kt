package com.reyaz.core.network.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.RemoteKeys
import com.reyaz.core.database.StockEntity
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.api.OverviewApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class StockRemoteMediator(
    private val db: GrowwDatabase,
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService,
    private val query: String
) : RemoteMediator<Int, StockEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StockEntity>
    ): MediatorResult = withContext(Dispatchers.IO) {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return@withContext MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKeys =
                        lastItem?.ticker?.let { db.remoteKeysDao().remoteKeysTicker(it) }
                    remoteKeys?.nextKey ?: return@withContext MediatorResult.Success(true)
                }
            }

            val response = alphaVantageApiService.fetchTopGainersLosers()
            val body = response.body() ?: return@withContext MediatorResult.Success(true)

            val topGainers = body.topGainers ?: emptyList()
            val topLosers = body.topLosers ?: emptyList()

            val combinedStocks = topGainers.plus(topLosers).mapNotNull { dto ->
                dto?.ticker?.let {
                    StockEntity(
                        ticker = it,
                        price = dto.price,
                        changeAmount = dto.changeAmount,
                        changePercentage = dto.changePercentage,
                    )
                }
            }

            val enrichedStocks = combinedStocks.mapNotNull { stock ->
                val overviewResponse = overviewApiService.fetchCompanyOverview(stock.ticker)
                if (overviewResponse.isSuccessful) {
                    overviewResponse.body()?.let { overview ->
                        stock.copy(name = overview.companyName, url = overview.image)
                    }
                } else null
            }

            val endOfPaginationReached = enrichedStocks.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
                    db.growwDao().clearAll()
                }

                val keys = enrichedStocks.map { stock ->
                    RemoteKeys(
                        ticker = stock.ticker,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (endOfPaginationReached) null else page + 1
                    )
                }

                db.remoteKeysDao().insertAll(keys)
                db.growwDao().insertAll(enrichedStocks)
            }

            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
