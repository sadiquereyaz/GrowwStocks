package com.reyaz.feature.product_list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.product_list.StockListUiState
import com.reyaz.feature.product_list.domain.repository.StockListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StockListViewModel(
    private val repository: StockListRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockListUiState())
    val uiState = _uiState.asStateFlow()


    private val stockTypeIndex: Int = savedStateHandle.get<Int>("type") ?: 0
    val pagedStocks =
        repository.getPagedStocks(type = StockType.entries[stockTypeIndex]).cachedIn(viewModelScope)

    fun refreshStocks(showRefreshIcon: Boolean) {

    }


}