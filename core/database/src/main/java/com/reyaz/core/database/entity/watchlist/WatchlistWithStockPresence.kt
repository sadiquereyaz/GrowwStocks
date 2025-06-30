package com.reyaz.core.database.entity.watchlist

data class WatchlistWithStockPresence(
    val watchlistId: Long,
    val name: String,
    val isPresent: Boolean
)
