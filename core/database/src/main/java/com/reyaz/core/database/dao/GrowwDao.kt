package com.reyaz.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.database.StockEntity

@Dao
interface GrowwDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<StockEntity>)

    @Query("SELECT * FROM stocks")
    fun pagingSource(): PagingSource<Int, StockEntity>

    @Query("DELETE FROM stocks")
    suspend fun clearAll()
}
