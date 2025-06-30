package com.reyaz.feature.product_detail.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import com.reyaz.core.ui.theme.extendedColorScheme

@Composable
fun StockChart(points: List<Point>) {
    val windowSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current

    val screenWidthDp = with(density) { windowSize.width.toDp() }

    val calculatedStepSize = if (points.isNotEmpty()) {
        ((screenWidthDp) / points.size)
    } else {
        1.dp
    }

    val lineChartData = LineChartData(
        paddingTop = 48.dp,
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        backgroundCornerRadius = CornerRadius(8f),
                        backgroundColor = MaterialTheme.extendedColorScheme.brandColor,
                        labelColor = MaterialTheme.colorScheme.onPrimary,
                        backgroundAlpha = 0.9f,
                        paddingBetweenPopUpAndPoint = 20.dp,
                        labelSize = 14.sp,
                        popUpLabel = { x, y ->
                            "  ₹${y.toInt()} | ${x.toInt()}  " // todo
                        }
                    ),
                    dataPoints = points,
                    lineStyle = LineStyle(
                        color = MaterialTheme.colorScheme.primary,
//                        lineType = LineType.SmoothCurve(isDotted = false),
                        lineType = LineType.Straight(isDotted = false),
                        alpha = 1f,
                        width = 2f,
                        style = Stroke(width = 3f),
                    ),
                    selectionHighlightPoint = SelectionHighlightPoint(
                        color = MaterialTheme.extendedColorScheme.brandColor,
                        alpha = 0.8f,
                        radius = 2.dp
                    ),
                    shadowUnderLine = ShadowUnderLine(
//                        alpha = 0.2f,
                        color = Color.Transparent
                        /*brush = Brush.verticalGradient(
                            colors = listOf(MaterialTheme.colorScheme.primary, Color.Transparent)
                        )*/
                    ),
                    intersectionPoint = IntersectionPoint(
                        color = MaterialTheme.colorScheme.primary,
                        alpha = 1f,
                        radius = 0.dp
                    ),
                )
            )
        ),
        // X-axis with calculated step size based on screen width
        xAxisData = AxisData.Builder()
            .axisStepSize(calculatedStepSize) // Dynamic step size
            .backgroundColor(Color.Transparent)
            .steps(points.size - 1)
            .labelData { _ -> "" }
            .axisLineColor(Color.Transparent)
            .axisLabelColor(Color.Transparent)
            .labelAndAxisLinePadding(0.dp)
            .build(),
        // Y-axis with no visible elements
        yAxisData = AxisData.Builder()
            .steps(10)
            .backgroundColor(Color.Transparent)
            .labelData { _ -> "" }
            .axisLineColor(Color.Transparent)
            .axisLabelColor(Color.Transparent)
            .labelAndAxisLinePadding(0.dp)
            .build(),
//        backgroundColor = Color.Transparent,
        backgroundColor = MaterialTheme.colorScheme.surface,
        // Disable scrolling and zooming for dense continuous graph
        isZoomAllowed = false,
        paddingRight = 0.dp,
        bottomPadding = 0.dp,
        containerPaddingEnd = 0.dp,
        gridLines = GridLines(
            enableHorizontalLines = false,
            enableVerticalLines = false
        )
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}
/*


package com.reyaz.feature.product_detail.presentation.archive

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import com.reyaz.core.ui.theme.extendedColorScheme

@Composable
fun StockChart(points: List<Point>) {
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        backgroundCornerRadius = CornerRadius(1f),
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                        paddingBetweenPopUpAndPoint = 20.dp,
                        labelSize = 14.sp,
                        labelColor = MaterialTheme.colorScheme.onPrimaryContainer,

                        ),
                    dataPoints = points,
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
                        radius = 0.dp
                    ),

                    )
            )
        ),
        xAxisData = AxisData.Builder()
            .axisStepSize(1.dp)
            .backgroundColor(Color.Transparent)
            .steps(points.size - 1)
            .labelData { i -> 
                // You can format dates here
                "${i.toInt()}"
            }
            .labelAndAxisLinePadding(15.dp)
            .build(),
        yAxisData = AxisData.Builder()
            .steps(10)
            .backgroundColor(Color.Transparent)
            .labelAndAxisLinePadding(20.dp)
            .labelData { i ->
                // Format price values
                "₹${i.toInt()}"
            }.build(),
        backgroundColor = Color.Black
    )
    
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}*/
