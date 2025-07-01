package com.reyaz.core.database

import com.reyaz.core.common.model.Stock
import com.reyaz.core.database.entity.StockTable

fun StockTable.toDomain(): Stock {
    return Stock(
        name = name,
        price = price,
        changeAmount = changeAmount,
        changePercentage = changePercentage,
        url = url,
        type = type,
        ticker = ticker
    )
}