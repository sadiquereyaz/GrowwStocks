package com.reyaz.growwstocks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.reyaz.core.common.navigation.Route
import com.reyaz.feature.home.presentation.HomeScreen
import com.reyaz.feature.home.presentation.HomeViewModel
import com.reyaz.feature.product_detail.presentation.StockChartScreen
import com.reyaz.feature.product_detail.presentation.StockDetailViewModel
import com.reyaz.feature.product_list.presentation.StockListScreen
import com.reyaz.feature.product_list.presentation.StockListViewModel
import com.reyaz.watchlist.presentation.AllWatchlistsScreen
import com.reyaz.watchlist.presentation.WatchlistStocksScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        startDestination =
            Route.Home
//            Route.StockDetailRoute()
//        Route.AllWatchlist()
                ,

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
                navigateToDetail = { id, name, url ->
                    navController.navigate(
                        Route.StockDetailRoute(
                            ticker = id,
                            title = name,
                            logoUrl = url.ifEmpty { null }
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
            StockListScreen(
                uiState = uiState,
                stocks = stocks,
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.StockDetailRoute(
                            ticker = id,
//                            price = "$43",
                            title = name
                        )
                    )
                },
                onRefresh = { viewModel.refreshStocks(true) }
            )
        }

        composable<Route.AllWatchlist> {
            AllWatchlistsScreen(
                onWatchlistClick = { id, name ->
                    navController.navigate(
                        Route.WatchlistStocksRoute(
                            watchlistId = id,
                            title = name
                        )
                    )
                }
            )
        }
        composable<Route.WatchlistStocksRoute> {
            WatchlistStocksScreen(
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.StockDetailRoute(
                            ticker = id,
//                            price = "$43",
                            title = name
                        )
                    )
                }
            )
        }

        composable<Route.StockDetailRoute> {
            val viewModel: StockDetailViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            StockChartScreen(
                uiState = uiState,
            )
        }


    }
}

