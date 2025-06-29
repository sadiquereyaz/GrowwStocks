package com.reyaz.feature.product_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.core.common.Resource
import com.reyaz.core.network.domain.TimePeriod
import com.reyaz.feature.product_detail.data.StockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockDetailViewModel (
    private val repository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadStockData("TORNTPHARM.BSE", TimePeriod.FIVE_YEAR)
    }
    
    private fun loadStockData(symbol: String, period: TimePeriod = TimePeriod.FIVE_YEAR) {

        updateState { it.copy(
            currentSymbol = symbol,
            currentPeriod = period,
            stockData = Resource.Loading()
        ) }

        
        viewModelScope.launch {
//            _stockData.value = Resource.Loading()
           val response = repository.getFilteredStockData(symbol, period)
            updateState { it.copy(
                stockData = response
            ) }
        }
    }
    
    fun changePeriod(period: TimePeriod) {
//        if (currentSymbol.isNotEmpty()) {
//            loadStockData(currentSymbol, period)
//        }
    }
    
    fun retry() {
//        if (currentSymbol.isNotEmpty()) {
//            loadStockData(currentSymbol, _currentPeriod.value ?: TimePeriod.FIVE_YEAR)
//        }
    }

    private fun updateState(update: (StockDetailUiState) -> StockDetailUiState) {
        _uiState.value = update(_uiState.value)
    }
}