package com.reyaz.growwstocks

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
import com.reyaz.growwstocks.app_bar.presentation.AppBarEvent
import com.reyaz.growwstocks.app_bar.presentation.MainUiState
import com.reyaz.growwstocks.navigation.AppNavHost
import androidx.compose.foundation.layout.padding

@Composable
fun GrowwStocksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    uiState: MainUiState,
    onEvent: (AppBarEvent) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        topBar = {
            GrowwTopAppBar(
                navController = navController,
                onSearchClick = {},
                isInWatchlist = true,
                onSaveClick = {},
                navBackStackEntry = navBackStackEntry,
                currentDestination = currentDestination,
                toggleTheme = { onEvent(AppBarEvent.ToggleTheme) },
                themeMode = uiState.themeMode,

                searchQuery = uiState.searchQuery,
                onSearchQueryChange = { onEvent(AppBarEvent.UpdateSearchQuery(it)) },
                isSearchActive = uiState.isSearchActive,
                toggleSearchActiveState = { onEvent(AppBarEvent.ToggleSearch) }
            )
        },
        bottomBar = {
            if (!uiState.isSearchActive || uiState.searchQuery.isBlank())
                GrowwBottomNavBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
        },
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
