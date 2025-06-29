package com.reyaz.core.network.di

import com.reyaz.core.network.BuildConfig
import com.reyaz.core.network.data.paging.StocksRemoteRepository
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.core.network.data.remote.api.OverviewApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .readTimeout(15L, TimeUnit.SECONDS)
            .connectTimeout(15L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single(named("alphaVantageRetrofit")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ALPHA_VINTAGE_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single(named("overviewRetrofit")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.OVERVIEW_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>(named("alphaVantageRetrofit"))
            .create(AlphaVantageApiService::class.java)
    }

    single {
        get<Retrofit>(named("overviewRetrofit"))
            .create(OverviewApiService::class.java)
    }

    single { StocksRemoteRepository(get(), get(), get()) }
}
