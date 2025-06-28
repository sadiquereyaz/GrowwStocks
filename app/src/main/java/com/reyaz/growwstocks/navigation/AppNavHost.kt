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
            val stocks = viewModel.pagedStocks.collectAsLazyPagingItems()
            HomeScreen(
                uiState = uiState,
                stocks = stocks,
                navigateToList = { navController.navigate(Route.StockList(title = it)) },
                navigateToDetail = { id, name ->
                    navController.navigate(
                        Route.Detail(
                            id = id,
                            title = name
                        )
                    )
                },
                onRefresh = { viewModel.refreshStocks(false) }
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