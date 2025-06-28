package com.reyaz.core.common.model

import androidx.compose.ui.graphics.Color

enum class StockType(val title: String, val color: androidx.compose.ui.graphics.Color) {
    UP(
        title = "Top Gainers",
        color = Color(0xFF00FF00)
    ),
    DOWN("Top Losers", color = Color(0xFFFF0F00))
}