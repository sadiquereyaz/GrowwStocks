package com.reyaz.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reyaz.core.common.model.StockType

@Entity(tableName = "stocks")
data class StockEntity(
    @PrimaryKey val ticker: String,
    val name: String? = null,
    val price: Float?,
    val changeAmount: Float?,
    val changePercentage: Float?,
    val url: String? = null,
    val type: StockType?,
    val createdOn: Long
)