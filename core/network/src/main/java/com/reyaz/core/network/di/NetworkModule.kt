package com.reyaz.core.network.di

import com.reyaz.core.network.AlphaVantageApiService
import com.reyaz.core.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{
    single {
        OkHttpClient.Builder()
            .readTimeout(timeout = 15L, unit = TimeUnit.SECONDS)
            .connectTimeout(timeout = 15L, unit = TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(AlphaVantageApiService::class.java)
    }
}
