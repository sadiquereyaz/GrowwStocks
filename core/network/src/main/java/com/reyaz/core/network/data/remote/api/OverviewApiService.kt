package com.reyaz.core.network.data.remote.api

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.remote.dto.CompanyDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OverviewApiService {
    @GET("api/v3/profile/{ticker}?apikey=${BuildConfig.OVERVIEW_API_KEY}")
    suspend fun fetchCompanyOverview(
        @Path("ticker") ticker: String,
    ): Response<List<CompanyDetailsResponse>>
}