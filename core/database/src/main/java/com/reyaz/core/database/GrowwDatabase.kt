package com.reyaz.core.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = []
)
abstract class GrowwDatabase : RoomDatabase() {


    companion object {
         const val DATABASE_NAME = "groww_database"
    }
}