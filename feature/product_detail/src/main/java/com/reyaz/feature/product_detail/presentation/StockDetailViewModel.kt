package com.reyaz.feature.product_detail.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyaz.core.common.Resource
import com.reyaz.core.network.domain.TimePeriod
import com.reyaz.feature.product_detail.data.StockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init


private const val TAG = "Stock_Detail_ViewModel"
class StockDetailViewModel(
    private val repository: StockRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(StockDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val ticker = stateHandle.get<String>("ticker") ?: "TORNTPHARM"
    private val url = stateHandle.get<String>("logoUrl")

    init {
        loadStockData(symbol = ticker, TimePeriod.FIVE_YEAR)
        loadCompanyInfo(ticker)
//        updateState {
//            it.copy(isLoading = false)
//        }
    }

    private fun loadCompanyInfo(ticker: String) {
        viewModelScope.launch {
            when(val response = repository.getCompanyInfo(ticker)){
                is Resource.Success -> {
                    Log.d(TAG, "loadCompanyInfo: ${response.data}")
                    updateState {
                        it.copy(
                            companyData = response.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    Log.d(TAG, "loadCompanyInfo: ${response.message}")
                    updateState {
                        it.copy(error = response.message, isLoading = false)
                    }
                }
                else -> {}
            }
        }
    }

    private fun loadStockData(symbol: String, period: TimePeriod = TimePeriod.FIVE_YEAR) {
        Log.d(TAG, "URL: $url")
        updateState {
            it.copy(
                currentSymbol = symbol,
                currentPeriod = period,
//                isLoading = true,
                logoUrl = url
            )
        }

        viewModelScope.launch {
            when (val response = repository.getFilteredStockData(symbol, period)) {
                is Resource.Success -> {
                    Log.d(TAG, "loadStockData: ${response.data}")
                    updateState {
                        it.copy(
                            graphData = response.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    Log.d(TAG, "loadStockData: ${response.message}")
                    updateState {
                        it.copy(error = response.message, isLoading = false)
                    }
                }
                else -> {}

            }
        }
    }

    private fun updateState(update: (StockDetailUiState) -> StockDetailUiState) {
        _uiState.value = update(_uiState.value)
    }
}