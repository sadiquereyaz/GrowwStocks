package com.reyaz.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val ticker: String,
    val prevKey: Int?,
    val nextKey: Int?
)