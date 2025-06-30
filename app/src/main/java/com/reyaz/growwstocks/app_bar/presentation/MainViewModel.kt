package com.reyaz.growwstocks.app_bar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.core.common.model.ThemeMode
import com.reyaz.growwstocks.app_bar.data.repository.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel (private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadThemeMode()
    }

    fun onEvent(event: AppBarEvent) {
        when (event) {
            AppBarEvent.ToggleTheme -> toggleTheme()
            is AppBarEvent.SetThemeMode -> setThemeMode(event.mode)

            AppBarEvent.ToggleSearch -> toggleSearch()
            AppBarEvent.ActivateSearch -> activateSearch()
            AppBarEvent.DeactivateSearch -> deactivateSearch()
            is AppBarEvent.UpdateSearchQuery -> updateSearchQuery(event.query)
            AppBarEvent.ClearSearch -> clearSearch()

            AppBarEvent.ToggleBookmarks -> toggleBookmarks()
            AppBarEvent.ShowBookmarks -> showBookmarks()
            AppBarEvent.HideBookmarks -> hideBookmarks()

            AppBarEvent.CloseAllOverlays -> closeAllOverlays()

            is AppBarEvent.SetLoading -> setLoading(event.loading)
        }
    }

    private fun loadThemeMode() {
        viewModelScope.launch {
            themeRepository.getThemeMode().collect { mode ->
                _uiState.update {
                    it.copy(
                        themeMode = mode,
                        isThemeLoaded = true
                    )
                }
            }
        }
    }

    private fun toggleTheme() {
        viewModelScope.launch {
            val currentMode = _uiState.value.themeMode
            val newMode = when (currentMode) {
                ThemeMode.SYSTEM -> ThemeMode.LIGHT
                ThemeMode.LIGHT -> ThemeMode.DARK
                ThemeMode.DARK -> ThemeMode.SYSTEM
            }
            themeRepository.setThemeMode(newMode)
        }
    }

    private fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            themeRepository.setThemeMode(mode)
        }
    }

    private fun toggleSearch() {
        _uiState.update { currentState ->
            if (currentState.isSearchActive) {
                // Closing search - clear query
                currentState.copy(
                    isSearchActive = false,
                    searchQuery = ""
                )
            } else {
                // Opening search - close bookmarks if open
                currentState.copy(
                    isSearchActive = true,
                    showBookmarks = false
                )
            }
        }
    }

    fun activateSearch() {
        _uiState.update {
            it.copy(
                isSearchActive = true,
                showBookmarks = false
            )
        }
    }

    fun deactivateSearch() {
        _uiState.update {
            it.copy(
                isSearchActive = false,
                searchQuery = ""
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun clearSearch() {
        _uiState.update {
            it.copy(searchQuery = "")
        }
    }

    // Bookmark functions
    fun toggleBookmarks() {
        _uiState.update { currentState ->
            if (currentState.showBookmarks) {
                // Closing bookmarks
                currentState.copy(showBookmarks = false)
            } else {
                // Opening bookmarks - close search if active
                currentState.copy(
                    showBookmarks = true,
                    isSearchActive = false,
                    searchQuery = ""
                )
            }
        }
    }

    fun showBookmarks() {
        _uiState.update {
            it.copy(
                showBookmarks = true,
                isSearchActive = false,
                searchQuery = ""
            )
        }
    }

    fun hideBookmarks() {
        _uiState.update {
            it.copy(showBookmarks = false)
        }
    }

    // Utility functions
    fun closeAllOverlays() {
        _uiState.update {
            it.copy(
                isSearchActive = false,
                showBookmarks = false,
                searchQuery = ""
            )
        }
    }

    fun setLoading(loading: Boolean) {
        _uiState.update {
            it.copy(isLoading = loading)
        }
    }
}