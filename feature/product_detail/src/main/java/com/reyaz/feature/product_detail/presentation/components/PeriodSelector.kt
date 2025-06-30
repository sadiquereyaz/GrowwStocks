package com.reyaz.feature.product_detail.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.reyaz.core.network.domain.TimePeriod

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
//                    color = if (isSelected) Color.Black else Color.White
                )
            }
        }
    }
}