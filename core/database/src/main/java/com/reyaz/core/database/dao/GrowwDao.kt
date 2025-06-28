package com.reyaz.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.database.StockEntity

@Dao
interface GrowwDao {

    @Query("SELECT * FROM stocks ORDER BY ticker ASC")
    fun pagingSource(): PagingSource<Int, StockEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<StockEntity>)

    @Query("DELETE FROM stocks")
    suspend fun clearAll()
}
