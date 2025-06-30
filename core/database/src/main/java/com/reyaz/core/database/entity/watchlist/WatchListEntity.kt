package com.reyaz.core.database.entity.watchlist

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WatchlistEntity(
    @PrimaryKey(autoGenerate = true) val watchlistId: Long = 0L,
    val watchlistName: String,
    val createdAt: Long = System.currentTimeMillis()
)

