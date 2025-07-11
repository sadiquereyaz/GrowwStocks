package com.reyaz.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.reyaz.core.common.R
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import com.reyaz.core.ui.theme.extendedColorScheme


@Composable
fun StockItem(
    modifier: Modifier = Modifier,
    stock: Stock,
    onItemClick: () -> Unit
) {
    CustomCard(
        modifier = modifier.aspectRatio(12 / 9f),
        onClick = { onItemClick.invoke() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),

            ) {
            CustomCard(
                modifier = Modifier
                    .size(40.dp),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(stock.url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.groww_logo),
                    error = painterResource(R.drawable.groww_placeholder), //todo: change error image into gray tint
                    contentDescription = "company logo",
                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.clip(CircleShape),
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape),
                )
            }
            stock.name?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            stock.price?.let {
                Text(
                    text = "₹${it}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                // change price and percent
                Text(
                    text = "${stock.changeAmount} (${stock.changePercentage}%)",
                    fontSize = 12.sp,
                    color = if (stock.type == StockType.UP) MaterialTheme.extendedColorScheme.gainerColor else MaterialTheme.extendedColorScheme.loserColor,
                    lineHeight = 2.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}