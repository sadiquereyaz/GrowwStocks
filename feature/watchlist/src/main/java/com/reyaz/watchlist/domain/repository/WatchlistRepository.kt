package com.reyaz.watchlist.domain.repository

import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {
    fun getAllWatchlists(): Flow<List<WatchlistEntity>>
}