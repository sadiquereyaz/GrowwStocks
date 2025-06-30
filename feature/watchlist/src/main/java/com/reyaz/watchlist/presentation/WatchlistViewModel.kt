package com.reyaz.watchlist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.core.database.entity.watchlist.WatchlistStockEntity
import com.reyaz.watchlist.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: WatchlistRepository,
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
                repository.getAllWatchlists().collect { watchlists ->
                    _uiState.value = WatchlistUiState.Success(allWatchlist = watchlists)
                }
            } catch (e: Exception) {
                _uiState.value = WatchlistUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadWatchlistsForStock(ticker: String) {
        viewModelScope.launch {
            /*repository.getWatchlistsWithStockPresence(ticker)
                .onSuccess { list ->
                    _uiState.update { it.copy(watchlists = list, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.localizedMessage, isLoading = false) }
                }*/
        }
    }

    fun onAddNewWatchlist(name: String, ticker: String) {
        viewModelScope.launch {
            val watchlistId = repository.createWatchlist(name)
            repository.insertStockIfNotExists(
                WatchlistStockEntity(
                    "watchlistId",
                    "ticker",
                    "34r",
                    ""
                )
            )
//            repository.addStockToWatchlist(watchlistId, ticker)
//            loadWatchlistsForStock(ticker) // refresh state
        }
    }

    fun onToggleWatchlistChecked(watchlistId: Long, ticker: String, shouldAdd: Boolean) {
        viewModelScope.launch {
            if (shouldAdd) {
                repository.addStockToWatchlist(watchlistId, ticker)
            } else {
                repository.removeStockFromWatchlist(watchlistId, ticker)
            }
            loadWatchlistsForStock(ticker) // refresh state
        }
    }
}
