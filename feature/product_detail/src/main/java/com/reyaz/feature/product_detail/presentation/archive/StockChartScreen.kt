package com.reyaz.feature.product_detail.presentation.archive
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reyaz.core.common.Resource
import com.reyaz.core.network.domain.TimePeriod
import com.reyaz.feature.product_detail.data.ChartDataConverter
import com.reyaz.feature.product_detail.presentation.StockDetailUiState

@Composable
fun StockChartScreen(uiState: StockDetailUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        when (val data = uiState.stockData) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.Green)
                }
            }
            
            is Resource.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    data.message?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /*viewModel.retry()*/ },
                        //colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text("Retry", color = Color.White)
                    }
                }
            }
            
            is Resource.Success -> {
                val points = data.data?.let { ChartDataConverter.convertToPoints(it) }
                val (change, changePercent) = ChartDataConverter.calculatePriceChange(data.data!!)
                val currentPrice = data.data?.lastOrNull()?.close ?: 0.0
                
                // Stock Info Header
                Text(
                    text = "Torrent Pharmaceuticals",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = ChartDataConverter.formatPrice(currentPrice),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = ChartDataConverter.formatChange(change, changePercent),
                    color = if (change >= 0) Color.Green else Color.Red,
                    fontSize = 14.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Chart
                if (points!!.isNotEmpty()) {
                    StockChart(points = points)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Period Selector
                PeriodSelector(
                    currentPeriod = uiState.currentPeriod,
                    onPeriodSelected = { /*viewModel.changePeriod(it)*/ }
                )
            }
            
            null -> {
                // Initial state
            }
        }
    }
}

@Composable
fun PeriodSelector(
    currentPeriod: TimePeriod,
    onPeriodSelected: (TimePeriod) -> Unit
) {
    val periods = listOf(
        "1D" to TimePeriod.ONE_DAY,
        "1W" to TimePeriod.ONE_WEEK,
        "1M" to TimePeriod.ONE_MONTH,
        "1Y" to TimePeriod.ONE_YEAR,
        "5Y" to TimePeriod.FIVE_YEAR,
        "ALL" to TimePeriod.ALL
    )
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(periods) { (label, period) ->
            val isSelected = currentPeriod == period
            
            Button(
                onClick = { onPeriodSelected(period) },
                colors = ButtonDefaults.buttonColors(
                   // backgroundColor = if (isSelected) Color.Green else Color.Transparent
                ),
                border = if (!isSelected) BorderStroke(1.dp, Color.Gray) else null,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = label,
                    color = if (isSelected) Color.Black else Color.White
                )
            }
        }
    }
}