package com.reyaz.feature.home.presentation

import com.reyaz.feature.home.domain.Stock

data class HomeUiState(
    val isLoading: Boolean = true,
    val error : String? = null,
    val topGainer: List<Stock> = emptyList(),
    val topLoser: List<Stock> = emptyList(),

    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val searchResult: List<Stock> = emptyList()
)
