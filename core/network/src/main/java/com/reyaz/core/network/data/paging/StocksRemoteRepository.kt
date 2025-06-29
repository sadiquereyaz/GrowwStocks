package com.reyaz.core.network.data.paging

import android.util.Log
import androidx.room.withTransaction
import com.reyaz.core.common.Resource
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import com.reyaz.core.common.utils.TypeConvertor
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.entity.StockEntity
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.api.OverviewApiService
import com.reyaz.core.network.data.remote.dto.StockDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

private const val TAG = "STOCKS_REMOTE_REPOSITORY"

class StocksRemoteRepository(
    private val db: GrowwDatabase,
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService
) {

    fun refreshStocks(): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val response = alphaVantageApiService.fetchTopGainersLosers()
            if (!response.isSuccessful || response.code() != 200) {
                throw Exception("Failed to fetch top gainers and losers")
            } else {
                response.body()?.let { it ->
                    val topGainers = buildTopStockEntities(
                        stockDtoList = it.topGainers.orEmpty(),
                        type = StockType.UP
                    )

                    val topLosers = buildTopStockEntities(
                        stockDtoList = it.topLosers.orEmpty(),
                        type = StockType.DOWN
                    )

                    val combinedStocks = topGainers + topLosers

                    db.withTransaction {
                        db.growwDao().clearAll()
                        db.growwDao().insertAll(combinedStocks)
                    }
                }
            }
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            Log.e(TAG, "Failed to fetch top gainers and losers", e)
            emit(Resource.Error(e.message ?: "Failed to fetch top gainers and losers"))
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun buildTopStockEntities(
        stockDtoList: List<StockDto?>,
        type: StockType
    ): List<StockEntity> = coroutineScope {
        stockDtoList.orEmpty()
            .mapNotNull { dto ->
                async {
                    dto?.ticker?.let { ticker ->
                        val time = System.currentTimeMillis()
                        val nameAndUrl = fetchNameAndUrl(ticker)
                        if (nameAndUrl.isSuccess) {
                            val (name, url) = nameAndUrl.getOrNull()!!

                            StockEntity(
                                ticker = ticker,
                                price = dto.price?.let { TypeConvertor.roundOffString(it) },
                                changeAmount = dto.changeAmount?.let { TypeConvertor.roundOffString(it) },
                                changePercentage = dto.changePercentage?.let { TypeConvertor.roundOffString(it) },
                                createdOn = time,
                                type = type,
                                name = name,
                                url = url
                            )
                        } else null
                    }
                }
            }
            .mapNotNull { it.await() }
    }

    private suspend fun fetchNameAndUrl(ticker: String): Result<Pair<String?, String?>> =
        withContext(Dispatchers.IO) {
            try {
                val overviewResponse = overviewApiService.fetchCompanyOverview(ticker)
                if (overviewResponse.isSuccessful) {
                    overviewResponse.body()?.let { it ->
                        if (overviewResponse.code() == 200 && it.isNotEmpty()
                        ) {
                            return@withContext Result.success(Pair(it[0].companyName, it[0].image))
                        } else {
                            return@withContext Result.failure(Exception("Failed to fetch company overview"))
                        }
                    }
                        ?: return@withContext Result.failure(Exception("Failed to fetch company overview"))
                } else {
                    throw Exception("Failed to fetch company overview")
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}

