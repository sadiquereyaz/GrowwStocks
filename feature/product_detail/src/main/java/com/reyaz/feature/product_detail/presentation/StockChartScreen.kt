package com.reyaz.feature.product_detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.reyaz.core.common.R
import com.reyaz.core.ui.components.CustomCard
import com.reyaz.core.ui.components.DottedUnderlineText
import com.reyaz.core.ui.theme.extendedColorScheme
import com.reyaz.feature.product_detail.data.ChartDataConverter
import com.reyaz.feature.product_detail.presentation.components.StockChart

@Composable
fun StockChartScreen(
    modifier: Modifier = Modifier,
    uiState: StockDetailUiState
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            if (uiState.isLoading)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            else if (uiState.error != null)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                    ) {
                        Text("Retry", color = Color.White)
                    }
                }
            else {
                if (uiState.graphData != null) {
                    val points = uiState.graphData?.let { ChartDataConverter.convertToPoints(it) }
                    val (change, changePercent) = ChartDataConverter.calculatePriceChange(uiState.graphData!!)
                    val currentPrice = uiState.graphData.lastOrNull()?.close ?: 0.0

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        CustomCard(
                            modifier = Modifier.size(60.dp),
                        ) {
                            uiState.logoUrl?.let {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(uiState.logoUrl)
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(R.drawable.groww_logo),
                                    error = painterResource(R.drawable.groww_placeholder), //todo: change error image into gray tint
                                    contentDescription = "company logo",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .clip(CircleShape),
                                )
                            }
                        }
                        Column() {
                            Text(
                                text = ChartDataConverter.formatPrice(currentPrice),
//                    color = Color.White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = ChartDataConverter.formatChange(change, changePercent),
                                color = if (change >= 0) MaterialTheme.extendedColorScheme.gainerColor else MaterialTheme.extendedColorScheme.gainerColor,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (points!!.isNotEmpty()) {
                        StockChart(points = points)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider()

                uiState.companyData?.let {
                    it.name?.let {
                        DottedUnderlineText(
                            text = it,
                            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp),
                            fontSize = 24,
                            color = MaterialTheme.extendedColorScheme.brandColor
                        )
                    }
                    it.description?.let {
                        Text(
                            text = "Overview",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 8.dp)
                        )
                        Text(
                            text = it,
                            modifier = Modifier.padding(16.dp, 0.dp),
                            maxLines = 7
                        )
                    }
                    it.industry?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}


