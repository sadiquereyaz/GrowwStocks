package com.reyaz.core.database.entity.watchlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class StockEntity(
    @PrimaryKey val ticker: String,
    val name: String,
    val price: Double
)
