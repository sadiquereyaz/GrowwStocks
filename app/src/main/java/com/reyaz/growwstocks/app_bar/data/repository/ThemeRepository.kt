package com.reyaz.growwstocks.app_bar.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.reyaz.core.common.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")
class ThemeRepository (
    private val dataStore: DataStore<Preferences>
) {
    
    private val themeKey = stringPreferencesKey("theme_mode")
    
    fun getThemeMode(): Flow<ThemeMode> {
        return dataStore.data.map { preferences ->
            val themeName = preferences[themeKey] ?: ThemeMode.SYSTEM.name
            try {
                ThemeMode.valueOf(themeName)
            } catch (e: IllegalArgumentException) {
                ThemeMode.SYSTEM
            }
        }
    }
    
    suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[themeKey] = mode.name
        }
    }
}