package com.reyaz.core.database.entity.watchlist

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["watchlistId", "ticker"],
    foreignKeys = [
        ForeignKey(
            entity = WatchlistEntity::class,
            parentColumns = ["watchlistId"],
            childColumns = ["watchlistId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WatchlistStockEntity::class,
            parentColumns = ["ticker"],
            childColumns = ["ticker"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("watchlistId"), Index("ticker")]
)
data class WatchlistStockCrossRef(
    val watchlistId: Long,
    val ticker: String
)
