package com.reyaz.core.database.entity.watchlist

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "watchlists")
data class WatchlistEntity(
    @PrimaryKey(autoGenerate = true) val watchlistId: Long = 0L,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)

