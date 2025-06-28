package com.reyaz.feature.home.presentation

import com.reyaz.core.database.StockEntity
import com.reyaz.feature.home.domain.Stock

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error : String? = null,
    val topGainer: List<StockEntity> = emptyList(),
    val topLoser: List<StockEntity> = emptyList(),

    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val searchResult: List<Stock> = emptyList()
)
