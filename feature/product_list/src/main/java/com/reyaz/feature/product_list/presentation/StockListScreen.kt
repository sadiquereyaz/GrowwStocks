package com.reyaz.feature.product_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.reyaz.core.common.model.Stock
import com.reyaz.core.ui.components.StockItem
import com.reyaz.feature.product_list.StockListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String, String) -> Unit,
    uiState: StockListUiState,
    stocks: LazyPagingItems<Stock>,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = uiState.isRefreshing,
        onRefresh = { onRefresh() }
    ) {
        if (uiState.isLoading && !uiState.isRefreshing) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        } else
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = stocks.itemCount,
                    key = { stocks[it]?.ticker ?: it }
                ) { index ->
                    stocks[index]?.let {
                        StockItem(
                            stock = it,
                            onItemClick = { navigateToDetail(it.ticker, it.name ?: "Failed loading name") },)
                    }
                }
            }
    }
}
