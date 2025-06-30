package com.reyaz.watchlist.presentation

import com.reyaz.core.common.model.Stock
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence

sealed interface WatchlistUiState {
    data object Loading : WatchlistUiState
    data class Success(
        val allWatchlist: List<WatchlistEntity>,
        val watchlists: List<WatchlistWithStockPresence> = emptyList(),
        val stocks: List<Stock> = emptyList()
    ) : WatchlistUiState

    data class Error(val message: String) : WatchlistUiState
}
