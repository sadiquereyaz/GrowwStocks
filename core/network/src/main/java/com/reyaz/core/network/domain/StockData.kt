package com.reyaz.core.network.domain

import com.reyaz.core.database.entity.DailyPrice

data class StockData(
    val symbol: String,
    val lastRefreshed: String,
    val dailyPrices: List<DailyPrice>
)

enum class TimePeriod {
    ONE_DAY, ONE_WEEK, ONE_MONTH, ONE_YEAR, FIVE_YEAR, ALL
}