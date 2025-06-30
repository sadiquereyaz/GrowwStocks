package com.reyaz.feature.product_detail.presentation

import com.reyaz.core.common.Resource
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.network.data.remote.dto.CompanyOverview
import com.reyaz.core.network.domain.TimePeriod

data class StockDetailUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val graphData: List<DailyPrice>? = null,
    var logoUrl: String? = null,
    val currentPeriod: TimePeriod = TimePeriod.FIVE_YEAR,
    val currentSymbol: String = "",
    val companyData: CompanyOverview? = null,
)


