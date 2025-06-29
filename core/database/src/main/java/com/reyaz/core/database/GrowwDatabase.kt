package com.reyaz.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reyaz.core.database.dao.RemoteKeysDao
import com.reyaz.core.database.dao.GrowwDao
import com.reyaz.core.database.entity.RemoteKeys
import com.reyaz.core.database.entity.StockEntity


@Database(
    version = 3,
    entities = [StockEntity::class, RemoteKeys::class]
)
abstract class GrowwDatabase : RoomDatabase() {
    abstract fun growwDao(): GrowwDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    companion object {
         const val DATABASE_NAME = "groww_database"
    }
}