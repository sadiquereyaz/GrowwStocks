package com.reyaz.feature.product_detail.di

import com.reyaz.feature.product_detail.data.StockRepository
import com.reyaz.feature.product_detail.data.StockRepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stockDetailModule = module {

    single<StockRepository> { StockRepositoryImpl(get(), get()) }
    viewModel { com.reyaz.feature.product_detail.presentation.StockDetailViewModel(get()) }
}