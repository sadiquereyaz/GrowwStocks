package com.reyaz.core.database.di

import androidx.room.Room
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.GrowwDatabase.Companion.DATABASE_NAME
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(context = get(), klass = GrowwDatabase::class.java, name = DATABASE_NAME)
        .fallbackToDestructiveMigration(true)
        .build() }
}