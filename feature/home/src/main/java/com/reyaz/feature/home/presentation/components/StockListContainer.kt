package com.reyaz.feature.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.reyaz.core.database.StockEntity
import com.reyaz.core.ui.components.DottedUnderlineText

@Composable
fun StockListContainer(
    modifier: Modifier = Modifier,
    heading: String,
    list: List<StockEntity>,
    onItemClick: (Int, String) -> Unit,
    navigateToStockList: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = heading,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            DottedUnderlineText(
                text = "See all",
                color = MaterialTheme.colorScheme.primary,
                underlineThickness = 1.dp,
                dashLength = 8f,
                gapLength = 4f,
                onClick = { navigateToStockList() }
            )

        }

        val items = List(list.size) { index -> list[index] }
        val chunkedItems = items.chunked(2)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            chunkedItems.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { stock ->
                        Box(modifier = Modifier.weight(1f)) {
                            StockItem(
                                stock = stock,
                                onItemClick = { onItemClick(0, stock.name ?: "Groww") }
                            )
                        }
                    }
                    // Fill empty space if last row has only one item
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


/*
package com.reyaz.feature.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.reyaz.core.database.StockEntity

@Composable
fun StockListContainer(
    modifier: Modifier = Modifier,
    heading: String,
    list: LazyPagingItems<StockEntity>,
    onItemClick: (Int, String) -> Unit,
    navigateToStockList: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 4.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = heading)
            Text(
                text = "See all",
                modifier = Modifier.clickable { navigateToStockList() }
            )      // todo: underline
        }

        var count = 0
        while (list.itemCount > count) {
            Row {
                StockItem(
                    stock = list[count++] ?: StockEntity("UBL", "UBL", "0.0", "0.0", "0.0", "0.0"),
                    onItemClick = { */
/*onItemClick(it.id, it.name ?: "Groww")*//*
 }
                )
                if (count < list.itemCount)
                    StockItem(
                        stock = list[count++] ?: StockEntity(
                            "UBL",
                            "UBL",
                            "0.0",
                            "0.0",
                            "0.0",
                            "0.0"
                        ),
                        onItemClick = { */
/*onItemClick(it.id, it.name ?: "Groww")*//*
 }
                    )
            }

        }
        */
/*LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Adaptive(180.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
//            userScrollEnabled = false
        ) {
            items(
                count = list.itemCount,
                key = list.itemKey { it.ticker },
                contentType = list.itemContentType { "contentType" }
            ) { index ->
                val item = list[index]
                StockItem(
                    stock = item ?: StockEntity("UBL", "UBL", "0.0", "0.0", "0.0", "0.0"),
                    onItemClick = { *//*
*/
/*onItemClick(it.id, it.name ?: "Groww")*//*
*/
/* })
            }
        }*//*

    }
}
*/
