package com.reyaz.core.common.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data class AllWatchlist(val title: String = "Watchlist") : Route

    @Serializable
    data class WatchlistStocks(val title: String, val watchlistId: Long) : Route

    @Serializable
    data class StockList(val type: Int, val title: String) : Route

    @Serializable
    data class StockDetail(val title: String, val id: String) : Route

}