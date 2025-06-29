package com.reyaz.core.common.model

data class Stock(
    val name: String? = null,
    val price: Float?,
    val changeAmount: Float?,
    val changePercentage: Float?,
    val url: String? = null,
    val type: StockType?,
    val ticker: String
)