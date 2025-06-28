package com.reyaz.feature.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.home.domain.Stock

@Composable
fun StockListContainer(
    modifier: Modifier = Modifier,
    heading: String,
    list: List<Stock>,
    onItemClick: (Int, String) -> Unit,
    navigateToStockList: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(4.dp, 0.dp ,4.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = heading)
            Text(
                text = "See all",
                modifier = Modifier.clickable { navigateToStockList() }
            )      // todo: underline
        }
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Adaptive(180.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            userScrollEnabled = false
        ) {
            items(list) {
                StockItem(stock = it, onItemClick = { onItemClick(it.id, it.name ?: "Groww") })
            }
        }
    }
}