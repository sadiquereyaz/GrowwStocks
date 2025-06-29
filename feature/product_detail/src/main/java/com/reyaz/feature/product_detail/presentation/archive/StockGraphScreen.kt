package com.reyaz.feature.product_detail.presentation.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import com.reyaz.core.common.Resource
import com.reyaz.core.network.domain.MonthlyAdjusted
import org.koin.androidx.compose.koinViewModel

@Composable
fun StockGraphScreen(viewModel: StockDetailViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is Resource.Loading -> CircularProgressIndicator()
            is Resource.Error -> Text("Error: ${(state as Resource.Error).message}")
            is Resource.Success -> {
                val data = (state as Resource.Success<List<MonthlyAdjusted>>).data
                val chartData = data?.mapIndexed { index, item ->
                    Point(index.toFloat(), item.adjustedClose)
                }
                val lineChartData = LineChartData(
                    linePlotData = LinePlotData(
                        plotType = PlotType.Wave,
                        lines = listOf(Line(
                            dataPoints = chartData?: emptyList(),
                        ))
                    ),
//                    xAxisData = TODO(),
//                    yAxisData = TODO(),
//                    isZoomAllowed = TODO(),
//                    paddingTop = TODO(),
//                    bottomPadding = TODO(),
//                    paddingRight = TODO(),
//                    containerPaddingEnd = TODO(),
//                    backgroundColor = TODO(),
//                    gridLines = TODO(),
//                    accessibilityConfig = TODO()
                )
                LineChart(
                    lineChartData = lineChartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                )
                /*YLineChart(
                    data = chartData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    config = LineChartConfig(
                        lineColor = Color.Green,
                        strokeWidth = 2f,
                        gridLines = true
                    )
                )*/
            }
        }
    }
}

class LineChartConfig(
    val lineColor: Color = Color.Green,
    val strokeWidth: Float = 2f,
    val gridLines: Boolean = true,
    val pointColor: Color = Color.Red,
    val showPoints: Boolean = false,
    val backgroundColor: Color = Color.Transparent
)

