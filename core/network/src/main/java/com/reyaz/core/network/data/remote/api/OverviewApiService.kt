package com.reyaz.core.network.data.remote.api

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.remote.dto.CompanyOverviewResponse
import com.reyaz.core.network.data.remote.dto.GainerLoserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OverviewApiService {
    @GET("api/v3/profile/{ticker}?apiKey=${BuildConfig.OVERVIEW_API_KEY}")
    suspend fun fetchCompanyOverview(
        @Path("ticker") ticker: String,
    ): Response<CompanyOverviewResponse>
}