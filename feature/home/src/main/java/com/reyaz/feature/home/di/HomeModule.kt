package com.reyaz.feature.home.di

import com.reyaz.feature.home.data.repository.HomeRepository
import com.reyaz.feature.home.data.repository.HomeRepositoryImpl
import com.reyaz.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<HomeRepository>{ HomeRepositoryImpl(get(),get()) }
    viewModel { HomeViewModel(get()) }
}