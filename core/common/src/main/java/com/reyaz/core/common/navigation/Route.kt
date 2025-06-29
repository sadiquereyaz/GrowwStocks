package com.reyaz.core.common.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Watchlist : Route

    @Serializable
    data class StockList(val type: Int, val title: String) : Route

    @Serializable
    data class StockDetail(val title: String, val id: String) : Route

}