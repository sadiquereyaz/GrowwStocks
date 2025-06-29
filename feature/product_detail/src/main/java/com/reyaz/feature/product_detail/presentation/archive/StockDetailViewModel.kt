package com.reyaz.feature.product_detail.presentation.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.core.common.Resource
import com.reyaz.core.network.data.paging.StocksRemoteRepository
import com.reyaz.core.network.domain.MonthlyAdjusted
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockDetailViewModel(
    private val repository: StocksRemoteRepository
) : ViewModel() {
    /*fun onEvent(stockDetailEvent: StockDetailEvent) {

    }

    private val _uiState = MutableStateFlow(StockDetailUiState())
    val uiState = _uiState.asStateFlow()*/
    private val _state = MutableStateFlow<Resource<List<MonthlyAdjusted>>>(Resource.Loading())
    val state: StateFlow<Resource<List<MonthlyAdjusted>>> = _state

    init {
        fetch()
    }

    fun fetch(symbol: String = "TORNTPHARM.BSE") {
        viewModelScope.launch {
//            repository.fetchStockPrices(symbol).collect { _state.value = it }
        }
    }

}