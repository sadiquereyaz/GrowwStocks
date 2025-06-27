package com.reyaz.core.common

import kotlinx.serialization.Serializable

@Serializable
sealed class Route(val title: String = "Groww Stocks") {

    @Serializable
    data object Home : Route()
    @Serializable
    data object Watchlist : Route("Watchlist")
    @Serializable
    data class Detail(val id: String, val name: String) : Route(title = name)

}