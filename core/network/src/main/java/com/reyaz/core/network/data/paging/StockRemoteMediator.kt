/*
package com.reyaz.core.network.data.paging

import android.util.Log
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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.jvm.Throws

private const val TAG = "STOCK_REMOTE_MEDIATOR"

@OptIn(ExperimentalPagingApi::class)
class StockRemoteMediator(
    private val db: GrowwDatabase,
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService,
    private val query: String
) : RemoteMediator<Int, StockEntity>() {

    private var hasLoadedInitially = false

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StockEntity>
    ): MediatorResult = withContext(Dispatchers.IO) {
        try {
            // prevent auto-refresh
            if (loadType == LoadType.REFRESH && hasLoadedInitially) {
                Log.d(TAG, "Skipping automatic refresh to enforce manual refresh only")
                return@withContext MediatorResult.Success(endOfPaginationReached = false)
            }

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
//            Log.d(TAG, "Fetching overview: ${overviewApiService.fetchCompanyOverview("TGE").body()}")
//            throw Exception("Test Exception")
            val response = alphaVantageApiService.fetchTopGainersLosers()
            Log.d(TAG, "Response from remote: $response")
            Log.d(TAG, "Response from remote: ${response.body()}")
            val body = response.body() ?: return@withContext MediatorResult.Success(true)

            val topGainers = body.topGainers ?: emptyList()
            val topLosers = body.topLosers ?: emptyList()

            val combinedStocks = topGainers.plus(topLosers).take(2).mapNotNull { dto ->
                dto?.ticker?.let {
                    StockEntity(
                        ticker = it,
                        price = dto.price,
                        changeAmount = dto.changeAmount,
                        changePercentage = dto.changePercentage,
                    )
                }
            }

            val enrichedStocks = combinedStocks.map { stock ->
                async {
                    try {
                        val overviewResponse = overviewApiService.fetchCompanyOverview(stock.ticker)
                        if (overviewResponse.isSuccessful) {
                            overviewResponse.body()?.let { overview ->
                                if (overview.isNotEmpty())
                                    stock.copy(
                                        name = overview[0].companyName,
                                        url = overview[0].image
                                    )
                                else
                                    null
                            }
                        } else if (overviewResponse.code() == 409) throw Exception("Limit reached")
                        else null
                    } catch (e: Exception) {
                        Log.e(TAG, "Error fetching overview for ${stock.ticker}: ${e.message}")
                        Log.e(TAG, "Reason: ", e)
                        null
                    }
                }
            }.awaitAll().filterNotNull()

            Log.d(TAG, "Enriched stocks: $enrichedStocks")
            val endOfPaginationReached = enrichedStocks.isEmpty()

            Log.d(TAG, "End of pagination reached: $endOfPaginationReached")
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
//                    db.growwDao().clearAll()
                }

                val keys = enrichedStocks.map { stock ->
                    Log.d(TAG, "Inserting keys for ${stock.ticker}")
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
*/
