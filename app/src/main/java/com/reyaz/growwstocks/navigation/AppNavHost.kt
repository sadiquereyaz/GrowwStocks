package com.reyaz.growwstocks.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.reyaz.core.common.Route

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        startDestination = Route.Home,
        navController = navController,
    ) {
        composable<Route.Home> {
//            HomeScreen(
//                onNavigateToWatchlist = { navController.navigate(Route.Watchlist) },
//                onNavigateToDetails = { id, name -> navController.navigate(Route.Details(id, name)) }
//            )
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