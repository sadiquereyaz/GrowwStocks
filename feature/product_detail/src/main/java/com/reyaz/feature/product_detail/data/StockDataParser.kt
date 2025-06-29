package com.reyaz.feature.product_detail.data

import co.yml.charts.common.model.Point
import com.google.gson.Gson
import com.reyaz.core.network.data.remote.dto.AlphaVantageResponse
import com.reyaz.core.network.domain.TimePeriod
import java.text.SimpleDateFormat
import java.util.*

class StockDataParser {
    
    fun parseAndConvertToPoints(jsonResponse: String): List<Point> {
        val gson = Gson()
        val response = gson.fromJson(jsonResponse, AlphaVantageResponse::class.java)
        
        val points = mutableListOf<Point>()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        // Sort by date (Alpha Vantage returns in descending order)

        response.timeSeries?.toList()
            ?.sortedBy { dateFormat.parse(it.first) }
            ?.forEachIndexed { index, (dateString, dailyData) ->
                val closePrice = dailyData.close.toFloat()
                points.add(Point(index.toFloat(), closePrice))
            }
        
        return points
    }
    
    // Filter data for different time periods
    fun filterDataByPeriod(
        jsonResponse: String, 
        period: TimePeriod
    ): List<Point> {
        val gson = Gson()
        val response = gson.fromJson(jsonResponse, AlphaVantageResponse::class.java)
        
        val calendar = Calendar.getInstance()
        val endDate = calendar.time
        
        // Calculate start date based on period
        when (period) {
            TimePeriod.ONE_DAY -> calendar.add(Calendar.DAY_OF_MONTH, -1)
            TimePeriod.ONE_WEEK -> calendar.add(Calendar.WEEK_OF_YEAR, -1)
            TimePeriod.ONE_MONTH -> calendar.add(Calendar.MONTH, -1)
            TimePeriod.ONE_YEAR -> calendar.add(Calendar.YEAR, -1)
            TimePeriod.FIVE_YEAR -> calendar.add(Calendar.YEAR, -5)
            TimePeriod.ALL -> calendar.add(Calendar.YEAR, -20) // or set to very old date
        }
        val startDate = calendar.time
        
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val points = mutableListOf<Point>()
        
        val filteredEntries = response.timeSeries?.filter { (dateString, _) ->
            val date = dateFormat.parse(dateString)
            date != null && date.after(startDate) && date.before(endDate)
        }?.toList()?.sortedBy { dateFormat.parse(it.first) }

        if (filteredEntries != null) {
            filteredEntries.forEachIndexed { index, (_, dailyData) ->
                val closePrice = dailyData.close.toFloat()
                points.add(Point(index.toFloat(), closePrice))
            }
        }
        
        return points
    }
}

/*
enum class TimePeriod {
    ONE_DAY, ONE_WEEK, ONE_MONTH, ONE_YEAR, FIVE_YEAR, ALL
}*/
