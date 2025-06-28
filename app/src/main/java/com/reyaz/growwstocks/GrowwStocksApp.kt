package com.reyaz.growwstocks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.reyaz.core.ui.components.GrowwBottomNavBar
import com.reyaz.core.ui.components.GrowwTopAppBar
import com.reyaz.growwstocks.navigation.AppNavHost

@Composable
fun GrowwStocksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination
    Scaffold(
        modifier = modifier,
        topBar = {
            GrowwTopAppBar(
                navController = navController,
                onSearchClick = {},
                isSaved = true,
                onSaveClick = {},
                navBackStackEntry = navBackStackEntry,
                currentDestination = currentDestination,
            )
        },
        bottomBar = {
            GrowwBottomNavBar(
                navController = navController,
                currentDestination = currentDestination
            )
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
