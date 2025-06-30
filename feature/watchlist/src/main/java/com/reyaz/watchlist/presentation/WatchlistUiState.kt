package com.reyaz.watchlist.presentation

import com.reyaz.core.common.model.Stock
import com.reyaz.core.database.entity.watchlist.WatchlistEntity

sealed interface WatchlistUiState {
    data object Loading : WatchlistUiState
    data class Success(
        val watchlists: List<WatchlistEntity>,
        val stocks: List<Stock> = emptyList()
    ) : WatchlistUiState

    data class Error(val message: String) : WatchlistUiState
}
