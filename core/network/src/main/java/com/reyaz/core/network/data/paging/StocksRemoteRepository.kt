package com.reyaz.core.network.data.paging

import android.util.Log
import androidx.room.withTransaction
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.StockEntity
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.api.OverviewApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "STOCKS_REMOTE_REPOSITORY"

class StocksRemoteRepository (
    private val db: GrowwDatabase,
    private val alphaVantageApiService: AlphaVantageApiService,
    private val overviewApiService: OverviewApiService
) {

    suspend fun refreshStocks() = withContext(Dispatchers.IO) {
//        Log.d(TAG, "OverviewResponse: ${overviewApiService.fetchCompanyOverview("QA")}")
//        throw Exception("test")
        val response = alphaVantageApiService.fetchTopGainersLosers()
        val body = response.body() ?: return@withContext

        val combinedStocks = (body.topGainers ?: emptyList()) + (body.topLosers ?: emptyList())
        val stockEntities = combinedStocks.mapNotNull { dto ->
            dto?.ticker?.let {
                StockEntity(
                    ticker = it,
                    price = dto.price,
                    changeAmount = dto.changeAmount,
                    changePercentage = dto.changePercentage
                )
            }
        }

        val enrichedStocks = stockEntities.map { stock ->
            try {
                val overviewResponse = overviewApiService.fetchCompanyOverview(stock.ticker)
                if (overviewResponse.isSuccessful) {
                    overviewResponse.body()?.let { overview ->
                        stock.copy(
                            name = overview[0].companyName,
                            url = overview[0].image
                        )
                    } ?: stock
                } else stock
            } catch (e: Exception) {
                stock
            }
        }

        db.withTransaction {
            db.growwDao().clearAll()
            db.growwDao().insertAll(enrichedStocks)
        }
    }
}
