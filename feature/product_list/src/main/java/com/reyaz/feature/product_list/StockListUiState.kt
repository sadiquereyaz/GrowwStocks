package com.reyaz.feature.product_list

data class StockListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false,
)