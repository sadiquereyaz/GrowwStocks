package com.reyaz.core.common.model

data class Stock(
    val name: String? = null,
    val price: Float?,
    val changeAmount: Float? = null,
    val changePercentage: Float? = null,
    val url: String? = null,
    val type: StockType? = null,
    val ticker: String
)