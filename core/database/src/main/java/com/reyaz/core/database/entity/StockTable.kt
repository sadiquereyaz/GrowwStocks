package com.reyaz.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reyaz.core.common.model.StockType

@Entity(tableName = "stocks_table")
data class StockTable(
    val ticker: String,
    @PrimaryKey val name: String,
    val price: Float?,
    val changeAmount: Float?,
    val changePercentage: Float?,
    val url: String? = null,
    val type: StockType?,
    val createdOn: Long
)