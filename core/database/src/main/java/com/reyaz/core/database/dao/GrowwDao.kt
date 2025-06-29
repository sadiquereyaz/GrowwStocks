package com.reyaz.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.entity.StockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrowwDao {

    @Query("SELECT * FROM stocks WHERE type = :type ORDER BY createdOn ASC")
    fun pagingSource(type: StockType): PagingSource<Int, StockEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<StockEntity>)

    @Query("DELETE FROM stocks")
    suspend fun clearAll()

    @Query("SELECT * FROM stocks WHERE type = :stockType ORDER BY createdOn ASC LIMIT(4)")
    fun getTopGainersLosers(stockType: StockType) : Flow<List<StockEntity>>
}
