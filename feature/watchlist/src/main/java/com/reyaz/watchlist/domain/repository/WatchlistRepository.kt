package com.reyaz.watchlist.domain.repository

import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistStockEntity
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {
    fun getAllWatchlists(): Flow<List<WatchlistEntity>>
    suspend fun getWatchlistsWithStockPresence(ticker: String): Result<List<WatchlistWithStockPresence>>
    suspend fun createWatchlist(name: String): Long
    suspend fun addStockToWatchlist(watchlistId: Long, ticker: String)
    suspend fun removeStockFromWatchlist(watchlistId: Long, ticker: String)
    suspend fun insertStockIfNotExists(stock: WatchlistStockEntity)

}