package com.reyaz.feature.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.home.presentation.components.StockListContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String, String) -> Unit,
    navigateToList: (Int, String) -> Unit,
    uiState: HomeUiState,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = uiState.isRefreshing,
        onRefresh = { onRefresh() }
    ) {
        if (uiState.isLoading && !uiState.isRefreshing) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        } else if (uiState.error != null) {
            Text(
                text = uiState.error,
                modifier = modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.error
            )
        } else
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (uiState.topGainer.isNotEmpty())
                    item {
                        StockListContainer(
                            modifier = Modifier,
                            onItemClick = { id, name -> navigateToDetail(id, name) },
                            navigateToStockList = {
                                navigateToList(
                                    StockType.UP.ordinal,
                                    "Top Gainers"
                                )
                            },
                            heading = "Top Gainers",
                            list = uiState.topGainer
                        )
                    }
                if (uiState.topLoser.isNotEmpty())
                    item {
                        StockListContainer(
                            modifier = Modifier,
                            onItemClick = { id, name -> navigateToDetail(id, name) },
                            navigateToStockList = {
                                navigateToList(
                                    StockType.DOWN.ordinal,
                                    "Top Losers"
                                )
                            },
                            heading = "Top Losers",
                            list = uiState.topLoser
                        )
                    }
            }
    }
}
