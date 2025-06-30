package com.reyaz.watchlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.watchlist.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel (
    private val watchlistRepository: WatchlistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WatchlistUiState>(WatchlistUiState.Loading)
    val uiState: StateFlow<WatchlistUiState> = _uiState.asStateFlow()

    init {
        fetchAllWatchlists()
    }

    private fun fetchAllWatchlists() {
        viewModelScope.launch {
            _uiState.value = WatchlistUiState.Loading
            try {
                watchlistRepository.getAllWatchlists().collect { watchlists ->
                    _uiState.value = WatchlistUiState.Success( watchlists = watchlists)
                }
            } catch (e: Exception) {
                _uiState.value = WatchlistUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
