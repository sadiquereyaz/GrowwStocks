package com.reyaz.feature.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.StockEntity
import com.reyaz.feature.home.domain.Stock
import com.reyaz.feature.home.presentation.components.StockListContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int, String) -> Unit,
    navigateToList: (String) -> Unit,
    uiState: HomeUiState,
    stocks: LazyPagingItems<StockEntity>,
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                stocks.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> { /* Initial loading UI */
                        }

                        loadState.append is LoadState.Loading -> { /* Pagination loading UI */
                        }

                        loadState.refresh is LoadState.Error -> { /* Error UI */
                        }

                        loadState.append is LoadState.Error -> { /* Pagination error UI */
                        }
                    }
                }
                if (uiState.topGainer.isNotEmpty())
                    item {
                        StockListContainer(
                            modifier = Modifier,
                            onItemClick = { id, name -> navigateToDetail(id, name) },
                            navigateToStockList = { navigateToList("Top Gainers") },
                            heading = "Top Gainers",
                            list = uiState.topGainer
                        )
                    }
                if (uiState.topLoser.isNotEmpty())
                    item {
                        StockListContainer(
                            modifier = Modifier,
                            onItemClick = { id, name -> navigateToDetail(id, name) },
                            navigateToStockList = { navigateToList("Top Losers") },
                            heading = "Top Losers",
                            list = uiState.topLoser
                        )
                    }
            }
    }
}
