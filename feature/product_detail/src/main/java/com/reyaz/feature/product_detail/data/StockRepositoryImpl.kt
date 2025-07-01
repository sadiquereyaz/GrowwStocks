package com.reyaz.feature.product_detail.data

import android.util.Log
import com.reyaz.core.common.Resource
import com.reyaz.core.database.dao.GrowwDao
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.dto.AlphaVantageResponse
import com.reyaz.core.network.data.remote.dto.CompanyOverview
import com.reyaz.core.network.domain.StockData
import com.reyaz.core.network.domain.TimePeriod
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "STOCK_REPOSITORY_IMPL"

class StockRepositoryImpl(
    private val alphaVantageApiService: AlphaVantageApiService,
    private val growwDao: GrowwDao
) : StockRepository {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override suspend fun getStockData(symbol: String): Resource<StockData> {
        return try {
            val response = alphaVantageApiService.getDailyTimeSeries(symbol = symbol)

            when {
                response.errorMessage != null || response.apiLimitExceeded != null -> {
                    Resource.Error("API Error: ${response.errorMessage ?: "Api Limit Exceeded"}")
                }

                response.note != null -> {
                    Resource.Error("API Limit: ${response.note}")
                }

                response.timeSeries == null || response.metaData == null -> {
                    Resource.Error("No data available for symbol: $symbol")
                }

                else -> {
                    val stockData = mapToStockData(response)
                    Resource.Success(stockData)
                }
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.localizedMessage}")
        }
    }

    override suspend fun getFilteredStockData(
        symbol: String,
        period: TimePeriod
    ): Resource<List<DailyPrice>> {
        val getLocalData = growwDao.getStockDetail()
        if (getLocalData.isNotEmpty()) {
            Log.d(TAG, "getFilteredStockData: ${getLocalData.size}")
            return Resource.Success(getLocalData)
        }
        return when (val result = getStockData(symbol)) {
            is Resource.Success -> {
                val filteredData = result.data?.let { filterDataByPeriod(it.dailyPrices, period) }
//                filteredData?.forEach {
//                    Log.d(TAG, "getFilteredStockData: $it")
//                }
                if (filteredData != null) {
                    growwDao.insertAllStockDetail(filteredData)
                }
                Resource.Success(filteredData)
            }

            is Resource.Error -> Resource.Error(result.message)
            is Resource.Loading -> Resource.Loading()
        }
    }

    suspend fun fetchLatestStockPrice(symbol: String): Resource<Double> { // Changed return type to more data type
        return try {
            val response = alphaVantageApiService.getGlobalQuote(symbol = symbol)
            if (response.isSuccessful) {
                val body = response.body()
                val price = body?.globalQuote?.price?.toDoubleOrNull()
                if (price != null) {
                    Resource.Success(price)
                } else {
                    Resource.Error("Price not available")
                }
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyOverview> {
        return try {
            val response = alphaVantageApiService.getCompanyOverview(symbol = symbol)
            Log.d(TAG, "getCompanyInfo: ${response.body()?.limitExceeded}")
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.limitExceeded == null) {
                        Resource.Success(it)
                    } else {
                        Resource.Error("API limit exceeded")
                    }
                } ?: Resource.Error("No data available")
            } else {
                Resource.Error("Invalid Url")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    private fun mapToStockData(response: AlphaVantageResponse): StockData {
        val metaData = response.metaData!!
        val timeSeries = response.timeSeries!!

        val dailyPrices = timeSeries.map { (dateString, dailyData) ->
            val date = dateFormat.parse(dateString)
            DailyPrice(
                date = dateString,
                open = dailyData.open.toDouble(),
                high = dailyData.high.toDouble(),
                low = dailyData.low.toDouble(),
                close = dailyData.close.toDouble(),
                volume = dailyData.volume.toLong(),
                timestamp = date?.time ?: 0L
            )
        }.sortedBy { it.timestamp }

        return StockData(
            symbol = metaData.symbol,
            lastRefreshed = metaData.lastRefreshed,
            dailyPrices = dailyPrices
        )
    }

    private fun filterDataByPeriod(
        dailyPrices: List<DailyPrice>,
        period: TimePeriod
    ): List<DailyPrice> {

        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis

        when (period) {
            TimePeriod.ONE_DAY -> calendar.add(Calendar.DAY_OF_MONTH, -1)
            TimePeriod.ONE_WEEK -> calendar.add(Calendar.WEEK_OF_YEAR, -1)
            TimePeriod.ONE_MONTH -> calendar.add(Calendar.MONTH, -1)
            TimePeriod.ONE_YEAR -> calendar.add(Calendar.YEAR, -1)
            TimePeriod.FIVE_YEAR -> calendar.add(Calendar.YEAR, -5)
            TimePeriod.ALL -> return dailyPrices
        }
        val startTime = calendar.timeInMillis

        return dailyPrices.filter {
            it.timestamp in startTime..endTime
        }
    }
}
