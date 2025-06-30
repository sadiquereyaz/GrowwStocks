package com.reyaz.core.database.entity.watchlist

data class WatchlistWithStockPresence(
    val watchlistId: Long,
    val watchlistName: String,
    val isPresent: Boolean
)
