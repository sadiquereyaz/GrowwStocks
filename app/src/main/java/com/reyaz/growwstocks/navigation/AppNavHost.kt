package com.reyaz.growwstocks.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.reyaz.core.common.navigation.Route
import com.reyaz.feature.home.presentation.HomeScreen
import com.reyaz.feature.home.presentation.HomeViewModel
import com.reyaz.feature.product_detail.presentation.StockDetailViewModel
import com.reyaz.feature.product_detail.presentation.StockChartScreen
import com.reyaz.feature.product_list.presentation.StockListScreen
import com.reyaz.feature.product_list.presentation.StockListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        startDestination = Route.Home,
//        StockDetail("", "")
        navController = navController,
    ) {
        composable<Route.Home> {
            val viewModel: HomeViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState,
                navigateToList = { typeIndex, title ->
                    navController.navigate(
                        Route.StockList(
                            type = typeIndex,
                            title = title
                        )
                    )
                },
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.StockDetail(
                            id = id,
                            title = name
                        )
                    )
                },
                onRefresh = { viewModel.refreshStocks(true) }
            )
        }
        composable<Route.StockList> {
            val viewModel: StockListViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val stocks = viewModel.pagedStocks.collectAsLazyPagingItems()
            StockListScreen (
                uiState = uiState,
                stocks = stocks,
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.StockDetail(
                            id = id,
                            title = name
                        )
                    )
                },
                onRefresh = { viewModel.refreshStocks(true) }
            )
        }

        composable<Route.Watchlist> {
//            WatchlistScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable<Route.StockDetail> { backStackEntry ->
            /* val detailsRoute = backStackEntry.toRoute<Route.StockDetail>()
             val viewModel : StockDetailViewModel = koinViewModel()
             val uiState by viewModel.uiState.collectAsStateWithLifecycle()
             StockDetailScreen(
                 uiState = uiState,
                 onEvent = viewModel::onEvent,
             )*/

//            StockGraphScreen()
            val viewModel: StockDetailViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            StockChartScreen(
                uiState = uiState,
//                onEvent = viewModel::onEvent,
            )
        }
    }
}