package com.reyaz.feature.product_detail.presentation

sealed class StockDetailEvent {
    data object refresh : StockDetailEvent()
}