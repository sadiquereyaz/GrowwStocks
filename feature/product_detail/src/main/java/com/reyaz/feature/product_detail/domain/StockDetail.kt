package com.reyaz.feature.product_detail.domain

data class StockDetail(
    val ticker: String,
    val name: String,
    val url: String?,
    val description: String,
)