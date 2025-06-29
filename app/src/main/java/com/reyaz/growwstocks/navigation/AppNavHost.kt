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
import com.reyaz.feature.product_list.presentation.StockListScreen
import com.reyaz.feature.product_list.presentation.StockListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier =  modifier,
        startDestination = Route.Home,
        navController = navController,
    ) {
        composable<Route.Home> {
            val viewModel : HomeViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState,
                navigateToList = {typeIndex, title-> navController.navigate(Route.StockList(type = typeIndex, title = title)) },
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.Detail(
                            id = id,
                            title = name
                        )
                    )
                },
                onRefresh = { viewModel.refreshStocks(true) }
            )
        }
        composable<Route.StockList> {
            val viewModel : StockListViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val stocks = viewModel.pagedStocks.collectAsLazyPagingItems()
            StockListScreen (
                uiState = uiState,
                stocks = stocks,
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.Detail(
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

        composable<Route.Detail> { backStackEntry ->
//            val detailsRoute = backStackEntry.toRoute<Route.Detail>()
//            DetailsScreen(
//                id = detailsRoute.id,
//                name = detailsRoute.name,
//                onNavigateBack = { navController.popBackStack() }
//            )
        }
    }
}