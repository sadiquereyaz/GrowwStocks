package com.reyaz.core.network.data.remote.api

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.remote.dto.AlphaVantageResponse
import com.reyaz.core.network.data.remote.dto.GainerLoserResponse
import com.reyaz.core.network.data.remote.dto.VintageOverviewResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApiService {
    //@GET("query?function=TOP_GAINERS_LOSERS&apikey=${BuildConfig.ALPHA_VINTAGE_API_KEY}")
    @GET("query")
    suspend fun fetchTopGainersLosers(
        @Query("function") function: String = "TOP_GAINERS_LOSERS",
//        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): Response<GainerLoserResponse>

    //https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY_ADJUSTED&symbol=TORNTPHARM.BSE&apikey=YOUR_API_KEY
    @GET("query?function=OVERVIEW&apikey=${BuildConfig.ALPHA_VINTAGE_API_KEY}")
    suspend fun fetchCompanyOverview(@Query("symbol") symbol: String): Response<VintageOverviewResponse>

//    @GET("query?function=OVERVIEW&apikey=${BuildConfig.ALPHA_VINTAGE_API_KEY}")
//    suspend fun fetchMonthlyData(@Query("symbol") symbol: String): Response<VintageOverviewResponse>

    /*
    TIME_SERIES_INTRADAY for 1D

    TIME_SERIES_DAILY_ADJUSTED for 1W, 1M

    TIME_SERIES_WEEKLY_ADJUSTED for 1Y

    TIME_SERIES_MONTHLY_ADJUSTED for 5Y
    */

    @GET("query")
    suspend fun getMonthlyAdjusted(
        @Query("function") function: String = "TIME_SERIES_MONTHLY_ADJUSTED",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): Response<AlphaVantageResponse>

    @GET("query")
    suspend fun getDailyTimeSeries(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String,
        @Query("outputsize") outputSize: String = "full",
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): AlphaVantageResponse
}