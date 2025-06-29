package com.reyaz.feature.product_detail.presentation

import com.reyaz.core.common.Resource
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.network.domain.TimePeriod

data class StockDetailUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val stockData: Resource<List<DailyPrice>> = Resource.Loading(),
    val currentPeriod: TimePeriod = TimePeriod.FIVE_YEAR,
    val currentSymbol: String = "",
)


