package com.reyaz.growwstocks.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.reyaz.growwstocks.app_bar.presentation.MainViewModel
import com.reyaz.growwstocks.app_bar.data.repository.ThemeRepository
import com.reyaz.growwstocks.app_bar.data.repository.appPreferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appBarModule = module{
    single<DataStore<Preferences>> {
        androidContext().appPreferencesDataStore
    }
    single { ThemeRepository(get()) }
    viewModel { MainViewModel(get()) }
}