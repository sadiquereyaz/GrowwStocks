package com.reyaz.feature.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.reyaz.core.common.R
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.StockEntity
import com.reyaz.core.ui.components.CustomCard
import com.reyaz.feature.home.domain.Stock
import java.util.Locale


@Composable
fun StockItem(
    modifier: Modifier = Modifier,
    stock: StockEntity,
    onItemClick: () -> Unit
) {
    CustomCard(
        modifier = modifier.aspectRatio(10 / 9f),
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
                    error = painterResource(R.drawable.groww_logo), //todo: change error image into gray tint
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
//                    text = stock.percentChange.toUpDownPriceWithPercentChange(stock.type, stock.price),
                    text = stock.changeAmount ?: "no",
                    fontSize = 12.sp,
//                    color = stock.type.color,
                    lineHeight = 2.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

fun Float.toUpDownPriceWithPercentChange(type: StockType, price: Float): String {
    val changeAmount = this.times(price).div(100f)
    val formattedChangeAmount = String.format(Locale.US, "%.2f", changeAmount)

    return if (type == StockType.UP) {
        "+$formattedChangeAmount ($this%)"
    } else {
        "-$formattedChangeAmount ($this%)"
    }
}