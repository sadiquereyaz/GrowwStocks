package com.reyaz.core.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.reyaz.core.database.dao.RemoteKeysDao
import com.reyaz.core.database.dao.GrowwDao


@Database(
    version = 1,
    entities = [StockEntity::class, RemoteKeys::class]
)
abstract class GrowwDatabase : RoomDatabase() {
    abstract fun growwDao(): GrowwDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    companion object {
         const val DATABASE_NAME = "groww_database"
    }
}


@Entity(tableName = "stocks")
data class StockEntity(
    @PrimaryKey val ticker: String,
    val name: String? = null,
    val price: String?,
    val changeAmount: String?,
    val changePercentage: String?,
    val url: String? = null
)

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val ticker: String,
    val prevKey: Int?,
    val nextKey: Int?
)
