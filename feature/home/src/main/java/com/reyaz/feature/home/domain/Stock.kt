package com.reyaz.feature.home.domain

import com.reyaz.core.common.model.StockType

data class Stock(
    val id: Int = 0,
    val iconUrl: String? = null,
    val name: String? = "Tata Consumer Product Private",
    val price: Float?  = 939.35f,
    val type: StockType  = StockType.UP,
    val percentChange: Float = 1.63f,
)