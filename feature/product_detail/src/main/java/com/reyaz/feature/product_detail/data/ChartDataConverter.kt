package com.reyaz.feature.product_detail.data

import co.yml.charts.common.model.Point
import com.reyaz.core.database.entity.DailyPrice

object ChartDataConverter {
    
    fun convertToPoints(dailyPrices: List<DailyPrice>): List<Point> {
        return dailyPrices.mapIndexed { index, dailyPrice ->
            Point(index.toFloat(), dailyPrice.close.toFloat())
        }
    }
    
    fun calculatePriceChange(dailyPrices: List<DailyPrice>): Pair<Double, Double> {
        if (dailyPrices.size < 2) return Pair(0.0, 0.0)
        
        val firstPrice = dailyPrices.first().close
        val lastPrice = dailyPrices.last().close
        val change = lastPrice - firstPrice
        val changePercent = (change / firstPrice) * 100
        
        return Pair(change, changePercent)
    }
    
    fun formatPrice(price: Double): String {
        return "₹${String.format("%.2f", price)}"
    }
    
    fun formatChange(change: Double, changePercent: Double): String {
        val sign = if (change >= 0) "+" else ""
        return "$sign${formatPrice(change)} ($sign${String.format("%.2f", changePercent)}%)"
    }
}