package com.reyaz.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistStockCrossRef
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {
    @Query("""
    SELECT w.watchlistId, w.name,
    CASE WHEN ws.ticker IS NOT NULL THEN 1 ELSE 0 END AS isPresent
    FROM watchlists w
    LEFT JOIN watchlist_stock_cross_ref ws
    ON w.watchlistId = ws.watchlistId AND ws.ticker = :ticker
    ORDER BY w.name ASC
""")
    suspend fun getAllWatchlistsWithStockPresence(ticker: String): List<WatchlistWithStockPresence>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStockToWatchlist(crossRef: WatchlistStockCrossRef)

    @Query("""
    DELETE FROM watchlist_stock_cross_ref
    WHERE watchlistId = :watchlistId AND ticker = :ticker
""")
    suspend fun removeStockFromWatchlist(watchlistId: Long, ticker: String)

    @Query("SELECT * FROM watchlists ORDER BY createdAt DESC")
    fun getAllWatchlists(): Flow<List<WatchlistEntity>>
}