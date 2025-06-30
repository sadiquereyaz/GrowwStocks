package com.reyaz.growwstocks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.reyaz.growwstocks.app_bar.presentation.WatchlistSelectionBottomSheet
import com.reyaz.watchlist.presentation.WatchlistUiState
import com.reyaz.watchlist.presentation.WatchlistViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

                isInWatchlist = uiState.isBookMarked,
                onBookMarkClick = { onEvent(AppBarEvent.ToggleBottomSheet) },

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
        if (uiState.isBottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    onEvent(AppBarEvent.ToggleBottomSheet)
                },
            ) {
                val viewModel: WatchlistViewModel = koinViewModel()
                LaunchedEffect(Unit) {
                    viewModel.loadWatchlistsForStock("watchlistId")
                }
                when (val watchlistUiState = viewModel.uiState.collectAsState().value) {
                    is WatchlistUiState.Loading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    is WatchlistUiState.Success -> {
                        WatchlistSelectionBottomSheet(
                            watchlists = watchlistUiState.watchlists,
                            onToggleWatchlistChecked = { id, isChecked ->
                                viewModel.onToggleWatchlistChecked(id, "ticker", isChecked)
                            },
                            onAddNewWatchlist = { name ->
                                viewModel.onAddNewWatchlist("name", "ticker")
                            }
                        )
                    }

                    is WatchlistUiState.Error -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = watchlistUiState.message,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

            }
        }

    }
}
