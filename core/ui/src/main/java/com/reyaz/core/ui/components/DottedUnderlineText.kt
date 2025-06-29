package com.reyaz.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reyaz.core.ui.theme.extendedColorScheme

@Composable
fun DottedUnderlineText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.extendedColorScheme.brandColor,
    underlineColor: Color = color,
    underlineThickness: Dp = 1.dp,
    dashLength: Float = 8f,
    gapLength: Float = 6f,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = AnnotatedString(text),
        fontSize = 12.sp,
        lineHeight = 1.sp,
        modifier = modifier
            .clickable {  onClick?.invoke() }.drawBehind {
            val strokeWidthPx = underlineThickness.toPx()
            val y = size.height
            drawLine(
                color = underlineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = strokeWidthPx,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(dashLength, gapLength), 0f
                )
            )
        }
    )
}
