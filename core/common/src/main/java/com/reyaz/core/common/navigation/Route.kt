package com.reyaz.core.common.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data class AllWatchlist(val title: String = "Watchlist") : Route

    @Serializable
    data class WatchlistStocksRoute(val title: String, val watchlistId: Long) : Route

    @Serializable
    data class StockList(val type: Int, val title: String) : Route

    @Serializable
    data class StockDetailRoute(val title: String = "C-Pharma Pvt.", val ticker: String = "CLNNW", val logoUrl: String? = "https://img.freepik.com/free-vector/cryptocurrency-bitcoin-golden-coin-background_1017-31505.jpg?semt=ais_hybrid&w=740") : Route

}