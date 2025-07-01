package com.reyaz.feature.product_detail.data

import com.reyaz.core.common.Resource
import com.reyaz.core.database.entity.DailyPrice
import com.reyaz.core.network.data.remote.dto.CompanyOverview
import com.reyaz.core.network.domain.StockData
import com.reyaz.core.network.domain.TimePeriod

interface StockRepository {
    suspend fun getStockData(symbol: String): Resource<StockData>
    suspend fun getFilteredStockData(symbol: String, period: TimePeriod): Resource<List<DailyPrice>>
    suspend fun getCompanyInfo(symbol: String): Resource<CompanyOverview>

}
