package com.reyaz.core.common.navigation

import kotlinx.serialization.Serializable
import com.reyaz.core.common.model.StockType

sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Watchlist : Route

    @Serializable
    data class StockList(val title: String) : Route

    @Serializable
    data class Detail(val title: String, val id: String) : Route

}