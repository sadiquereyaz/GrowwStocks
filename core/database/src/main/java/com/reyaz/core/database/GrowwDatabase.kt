package com.reyaz.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reyaz.core.database.dao.RemoteKeysDao
import com.reyaz.core.database.dao.GrowwDao
import com.reyaz.core.database.dao.WatchListDao
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.database.entity.RemoteKeys
import com.reyaz.core.database.entity.StockTable
import com.reyaz.core.database.entity.watchlist.WatchlistStockEntity
import com.reyaz.core.database.entity.watchlist.WatchlistEntity
import com.reyaz.core.database.entity.watchlist.WatchlistStockCrossRef


@Database(
    version = 9,
    entities = [
        RemoteKeys::class,
        DailyPrice::class,
        WatchlistEntity::class,
        StockTable::class,
        WatchlistStockEntity::class,
        WatchlistStockCrossRef::class]
)
abstract class GrowwDatabase : RoomDatabase() {
    abstract fun growwDao(): GrowwDao
    abstract fun remoteKeysDao(): RemoteKeysDao // todo
    abstract fun watchListDao(): WatchListDao

    companion object {
        const val DATABASE_NAME = "groww_database"
    }
}