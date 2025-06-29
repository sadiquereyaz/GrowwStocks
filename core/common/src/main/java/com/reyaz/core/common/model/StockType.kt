package com.reyaz.core.common.model

import androidx.compose.ui.graphics.Color

enum class StockType(val title: String) {
    UP(
        title = "Top Gainers",
    ),
    DOWN("Top Losers")
}