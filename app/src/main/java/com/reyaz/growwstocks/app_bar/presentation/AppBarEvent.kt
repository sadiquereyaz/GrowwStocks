package com.reyaz.growwstocks.app_bar.presentation

import com.reyaz.core.common.model.ThemeMode
sealed class AppBarEvent {
    data object ToggleTheme : AppBarEvent()
    data class SetThemeMode(val mode: ThemeMode) : AppBarEvent()

    data object ToggleSearch : AppBarEvent()
    data class UpdateSearchQuery(val query: String) : AppBarEvent()
    data object ClearSearch : AppBarEvent()
    data object ActivateSearch : AppBarEvent()
    data object DeactivateSearch : AppBarEvent()

    data object ToggleBookmarks : AppBarEvent()
    data object ShowBookmarks : AppBarEvent()
    data object HideBookmarks : AppBarEvent()
    data object CloseAllOverlays : AppBarEvent()
    data class SetLoading(val loading: Boolean) : AppBarEvent()
}
