package com.reyaz.watchlist.data.repository

import com.reyaz.core.database.dao.WatchListDao
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.watchlist.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow

class WatchlistRepositoryImpl(
    private val watchlistDao: WatchListDao
) : WatchlistRepository {
    override fun getAllWatchlists(): Flow<List<WatchlistEntity>> =
        watchlistDao.getAllWatchlists()
}
