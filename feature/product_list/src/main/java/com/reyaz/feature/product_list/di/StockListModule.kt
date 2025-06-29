package com.reyaz.feature.product_list.di

import com.reyaz.feature.product_list.data.repository.StockListRepositoryImpl
import com.reyaz.feature.product_list.domain.repository.StockListRepository
import com.reyaz.feature.product_list.presentation.StockListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stockListModule = module {

    single<StockListRepository>{ StockListRepositoryImpl(get()) }
    viewModel { StockListViewModel(get(), get()) }
    
}