package com.reyaz.feature.product_list.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.reyaz.feature.product_list.StockListUiState
import com.reyaz.feature.product_list.domain.repository.StockListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StockListViewModel(
    private val repository: StockListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockListUiState())
    val uiState = _uiState.asStateFlow()

    val pagedStocks = repository.getPagedStocks().cachedIn(viewModelScope)

    fun refreshStocks(showRefreshIcon: Boolean) {

    }



}