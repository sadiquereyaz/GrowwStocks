package com.reyaz.core.network.data.remote.api

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.remote.dto.AlphaVantageResponse
import com.reyaz.core.network.data.remote.dto.CompanyOverview
import com.reyaz.core.network.data.remote.dto.GainerLoserResponse
import com.reyaz.core.network.data.remote.dto.GlobalQuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApiService {
    @GET("query")
    suspend fun fetchTopGainersLosers(
        @Query("function") function: String = "TOP_GAINERS_LOSERS",
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): Response<GainerLoserResponse>

    @GET("query")
    suspend fun getDailyAdjusted(
        @Query("function") function: String = "TIME_SERIES_DAILY_ADJUSTED",
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

    @GET("query")
    suspend fun getGlobalQuote(
        @Query("function") function: String = "GLOBAL_QUOTE",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): Response<GlobalQuoteResponse>

    @GET("query")
    suspend fun getCompanyOverview(
        @Query("function") function: String = "OVERVIEW",
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = BuildConfig.ALPHA_VINTAGE_API_KEY
    ): Response<CompanyOverview>
}