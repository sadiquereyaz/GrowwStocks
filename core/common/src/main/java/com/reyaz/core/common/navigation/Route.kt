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
    data class StockDetailRoute(val title: String, val ticker: String, val price: String) : Route

}