package com.reyaz.feature.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.reyaz.core.common.model.StockType
import com.reyaz.feature.home.domain.Stock
import com.reyaz.feature.home.presentation.components.StockListContainer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int, String) -> Unit,
    navigateToList: (String) -> Unit,
    uiState: HomeUiState,
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        StockListContainer(
            modifier = Modifier,
            onItemClick = { id, name -> navigateToDetail(id, name) },
            navigateToStockList = { navigateToList("Top Gainers") },
            heading = "Top Gainers",
            list = listOf(Stock(), Stock(), Stock(), Stock())
        )

        StockListContainer(
            modifier = Modifier,
            onItemClick = { id, name -> navigateToDetail(id, name) },
            navigateToStockList = { navigateToList("Top Losers") },
            heading = "Top Losers",
            list = listOf(
                Stock(type = StockType.DOWN),
                Stock(type = StockType.DOWN),
                Stock(type = StockType.DOWN),
                Stock(type = StockType.DOWN),
                Stock(type = StockType.DOWN)
            )
        )
    }
}
