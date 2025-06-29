package com.reyaz.feature.product_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.reyaz.core.ui.theme.extendedColorScheme

@Composable
fun StockDetailScreen(
    modifier: Modifier = Modifier,
    uiState: StockDetailUiState,
    onEvent: (StockDetailEvent) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth().padding(16.dp)
    ) {
        val steps = 4
        val pointsData: List<Point> =
            listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
        val xAxisData = AxisData.Builder()
//            .endPadding(16.dp)
//            .startPadding(10.dp)
            .startDrawPadding(0.dp)
            .labelAndAxisLinePadding(0.dp)
            .axisLineColor(MaterialTheme.colorScheme.primary)
            .axisLabelColor(MaterialTheme.colorScheme.primary)
            .axisStepSize(50.dp)
            .backgroundColor(MaterialTheme.colorScheme.surface)
            .steps(pointsData.size + 1)
            .labelData { i -> i.toString() }
            .labelAndAxisLinePadding(15.dp)
            .build()

        val yAxisData = AxisData.Builder()
            .steps(steps)
//            .startPadding(10.dp)
            .backgroundColor(MaterialTheme.colorScheme.primaryContainer)
            .axisLineColor(MaterialTheme.colorScheme.primary)
            .axisLabelColor(MaterialTheme.colorScheme.primary)
            .backgroundColor(MaterialTheme.colorScheme.surface)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i ->
                val yScale = 100 / steps
                (i * yScale).toString()
            }.build()

        val lineChartData = LineChartData(
            isZoomAllowed = false,
//            containerPaddingEnd = 10.dp,
            linePlotData = LinePlotData(
                plotType = PlotType.Line,
                lines = listOf(
                    Line(
                        selectionHighlightPopUp = SelectionHighlightPopUp(
                            backgroundCornerRadius = CornerRadius(15f),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            paddingBetweenPopUpAndPoint = 20.dp,
                            labelSize = 14.sp,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,

                        ),
                        dataPoints = pointsData,
                        lineStyle = LineStyle(
                            color = MaterialTheme.colorScheme.onSurface,
//                            lineType = LineType.SmoothCurve(isDotted =false),
                            lineType = LineType.Straight(isDotted =false),
                            alpha = 1f,
                            width = 3f,
                            style = Stroke(width = 3f),
                        ),

                       selectionHighlightPoint =  SelectionHighlightPoint(
                           color = MaterialTheme.extendedColorScheme.brandColor,
                           alpha = 0.8f,
                           radius = 2.dp    // selection line width
                       ),
                       shadowUnderLine =  ShadowUnderLine(
//                           color = MaterialTheme.colorScheme.primary,
                           alpha = 0.3f,
                           brush = Brush.verticalGradient(
                               colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
                           )
                       ),

                          intersectionPoint =  IntersectionPoint(
                              color = MaterialTheme.colorScheme.primary,
                              alpha = 1f,
                              radius = 2.dp    // selection line width
                          ),

                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
//            gridLines = GridLines(),
            backgroundColor = MaterialTheme.colorScheme.surface
        )

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )
    }
}