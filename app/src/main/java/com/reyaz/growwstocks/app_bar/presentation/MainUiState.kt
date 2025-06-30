package com.reyaz.growwstocks.app_bar.presentation

import com.reyaz.core.common.model.ThemeMode

data class MainUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isThemeLoaded: Boolean = false,
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val isBookMarked: Boolean = false,
    val isBottomSheetVisible: Boolean = false,
    val isLoading: Boolean = false,
)