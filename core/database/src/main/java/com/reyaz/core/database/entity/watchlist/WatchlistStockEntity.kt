package com.reyaz.core.database.entity.watchlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchlistStockEntity(
    @PrimaryKey val ticker: String,
    val name: String,
    val price: String,
    val url: String
)
