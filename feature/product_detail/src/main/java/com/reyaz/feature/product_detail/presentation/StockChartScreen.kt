package com.reyaz.feature.product_detail.presentation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.reyaz.feature.product_detail.data.ChartDataConverter
import com.reyaz.feature.product_detail.presentation.components.PeriodSelector
import com.reyaz.feature.product_detail.presentation.components.StockChart

@Composable
fun StockChartScreen(
    modifier: Modifier = Modifier,
    uiState: StockDetailUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val data = uiState.stockData) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
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
                            color = MaterialTheme.colorScheme.error,
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
                
                Text(
                    text = ChartDataConverter.formatPrice(currentPrice),
//                    color = Color.White,
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

