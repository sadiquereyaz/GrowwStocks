package com.reyaz.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyPrice(
    val date: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Long,
    @PrimaryKey
    val timestamp: Long
)