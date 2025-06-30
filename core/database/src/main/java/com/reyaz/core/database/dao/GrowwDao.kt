package com.reyaz.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.database.entity.StockTable
import kotlinx.coroutines.flow.Flow

@Dao
interface GrowwDao {

    @Query("SELECT * FROM stocks_table WHERE type = :type ORDER BY createdOn ASC")
    fun pagingSource(type: StockType): PagingSource<Int, StockTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<StockTable>)

    @Query("DELETE FROM stocks")
    suspend fun clearAll()

    @Query("SELECT * FROM stocks_table WHERE type = :stockType ORDER BY createdOn ASC LIMIT(4)")
    fun getTopGainersLosers(stockType: StockType) : Flow<List<StockTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStockDetail(stock: List<DailyPrice>)

    @Query("SELECT * FROM DAILYPRICE ORDER BY date DESC")
    suspend fun getStockDetail(): List<DailyPrice>
}
