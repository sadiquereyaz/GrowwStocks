package com.reyaz.watchlist.di

import com.reyaz.watchlist.data.repository.WatchlistRepositoryImpl
import com.reyaz.watchlist.domain.repository.WatchlistRepository
import com.reyaz.watchlist.presentation.WatchlistViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val watchlistModule = module {
    single<WatchlistRepository> { WatchlistRepositoryImpl(get()) }
    viewModel {
        WatchlistViewModel(get())
    }
}