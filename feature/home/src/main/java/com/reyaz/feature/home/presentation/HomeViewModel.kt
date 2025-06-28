package com.reyaz.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.home.data.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
//    private val searchItemsUseCase: SearchItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    val pagedStocks = repository.getPagedStocks().flow
        .cachedIn(viewModelScope)

    /*    var isSearchActive by mutableStateOf(false)
            private set

        var searchQuery by mutableStateOf("")
            private set

        var searchResults by mutableStateOf<List<Item>>(emptyList())
            private set*/

    fun onSearchIconClicked() {
        updateState { it.copy(isSearchActive = true) }
    }
init {
    //refreshStocks()
}
    fun refreshStocks() {
        viewModelScope.launch {
            updateState { it.copy(isRefreshing = true) }
            try {
                //repository.refreshStocks()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                updateState { it.copy(isRefreshing = false) }

            }
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

