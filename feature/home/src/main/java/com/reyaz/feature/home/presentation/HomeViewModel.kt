package com.reyaz.feature.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reyaz.core.common.Resource
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "HOME_VIEW_MODEL"

class HomeViewModel(
    private val repository: HomeRepository,
//    private val searchItemsUseCase: SearchItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        getTopStocks()
    }

    fun refreshStocks(showRefreshIndicator: Boolean = true) {
        viewModelScope.launch {
            updateState { it.copy(isRefreshing = showRefreshIndicator) }
            try {
                repository.refreshStocks().collect { resource ->
                    Log.d(TAG, "refreshStocks: $resource")
                    when (resource) {
                        is Resource.Loading -> {
                            updateState { it.copy(isRefreshing = true, error = null) }
                        }

                        is Resource.Success -> {
                            updateState {
                                it.copy(
                                    isRefreshing = false,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            Log.d(TAG, "refreshStocks: ${resource.message}")
                            updateState {
                                it.copy(
                                    isRefreshing = false,
                                    isLoading = false,
                                    error = resource.message
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                updateState { it.copy(isRefreshing = false) }
            }
        }
    }

    private fun getTopStocks() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            launch {
                repository.getTopGainerLoser(StockType.UP).collect { topGainers ->
                    updateState { it.copy(topGainer = topGainers) }
                }
            }
            launch {
                repository.getTopGainerLoser(StockType.DOWN).collect { topLosers ->
                    updateState { it.copy(topLoser = topLosers) }
                }
            }
            delay(1000)
            if (uiState.value.topGainer.isEmpty() && uiState.value.topLoser.isEmpty()) {
                Log.d(TAG, "Refreshing...")
                refreshStocks(showRefreshIndicator = false)
            }
            updateState { it.copy(isLoading = false) }
        }
    }

    fun onSearchQueryChanged(query: String) {
        viewModelScope.launch {
            updateState { it.copy(searchQuery = query) }
            performSearch(query)
        }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            delay(400L)
            //searchResults = searchItemsUseCase(query)
        }
    }

    fun onSearchClosed() {
        updateState {
            it.copy(
                isSearchActive = false,
                searchQuery = "",
                searchResult = emptyList()
            )
        }
    }

    private fun updateState(update: (HomeUiState) -> HomeUiState) {
        _uiState.value = update(_uiState.value)
    }
}

