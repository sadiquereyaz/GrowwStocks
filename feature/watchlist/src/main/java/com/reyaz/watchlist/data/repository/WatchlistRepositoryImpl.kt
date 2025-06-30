package com.reyaz.watchlist.data.repository

import com.reyaz.core.database.dao.WatchListDao
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistStockCrossRef
import com.reyaz.core.database.entity.watchlist.WatchlistStockEntity
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence
import com.reyaz.watchlist.domain.repository.WatchlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WatchlistRepositoryImpl(
    private val dao: WatchListDao
) : WatchlistRepository {
    override fun getAllWatchlists(): Flow<List<WatchlistEntity>> =
        dao.getAllWatchlists()

    override suspend fun getWatchlistsWithStockPresence(ticker: String): Result<List<WatchlistWithStockPresence>> =
        withContext(Dispatchers.IO) {
            try {
                val list = dao.getWatchlistsWithStockPresence(ticker)
                Result.success(list)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    override suspend fun insertStockIfNotExists(stock: WatchlistStockEntity) =
        withContext(Dispatchers.IO) {
            dao.insertStock(stock) // DAO with OnConflictStrategy.IGNORE
        }

    override suspend fun createWatchlist(name: String): Long =
        withContext(Dispatchers.IO) {
            val watchlist = WatchlistEntity(name = name)
            dao.insertWatchlist(watchlist)
        }

    override suspend fun addStockToWatchlist(watchlistId: Long, ticker: String) =
        withContext(Dispatchers.IO) {
            dao.insertCrossRef(WatchlistStockCrossRef(watchlistId, ticker))
        }

    override suspend fun removeStockFromWatchlist(watchlistId: Long, ticker: String) =
        withContext(Dispatchers.IO) {
            dao.deleteCrossRef(watchlistId, ticker)


        }
}
