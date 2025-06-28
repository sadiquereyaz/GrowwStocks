package com.reyaz.core.network.data.remote.api

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.remote.dto.GainerLoserResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlphaVantageApiService {
    @GET("query?function=TOP_GAINERS_LOSERS&apikey=${BuildConfig.ALPHA_VINTAGE_API_KEY}")
    suspend fun fetchTopGainersLosers(): Response<GainerLoserResponse>
}