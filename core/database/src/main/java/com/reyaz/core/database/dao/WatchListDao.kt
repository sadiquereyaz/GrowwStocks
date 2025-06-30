package com.reyaz.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.common.navigation.Route
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistStockCrossRef
import com.reyaz.core.database.entity.watchlist.WatchlistStockEntity
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {
    @Query("""
    SELECT w.watchlistId, w.watchlistName,
    CASE WHEN ws.ticker IS NOT NULL THEN 1 ELSE 0 END AS isPresent
    FROM WatchlistEntity w
    LEFT JOIN WatchlistStockCrossRef ws
    ON w.watchlistId = ws.watchlistId AND ws.ticker = :ticker
    ORDER BY w.watchlistName ASC
""")

    suspend fun getWatchlistsWithStockPresence(ticker: String): List<WatchlistWithStockPresence>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStock(stock: WatchlistStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStockToWatchlist(crossRef: WatchlistStockCrossRef)

    @Query("""
    DELETE FROM WatchlistStockCrossRef
    WHERE watchlistId = :watchlistId AND ticker = :ticker
""")
    suspend fun removeStockFromWatchlist(watchlistId: Long, ticker: String)

    @Query("SELECT * FROM WatchlistEntity ORDER BY createdAt DESC")
    fun getAllWatchlists(): Flow<List<WatchlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(watchlist: WatchlistEntity): Long

}