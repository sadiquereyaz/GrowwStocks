package com.reyaz.watchlist.presentation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.reyaz.watchlist.presentation.component.WatchlistItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllWatchlistsScreen(
    modifier: Modifier = Modifier,
    viewModel: WatchlistViewModel = koinViewModel(),
    onWatchlistClick: (Long, String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllWatchlists()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is WatchlistUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is WatchlistUiState.Success -> {
            val watchlists = (uiState as WatchlistUiState.Success).allWatchlist
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(watchlists) { watchlist ->
                    WatchlistItem(
                        watchlist = watchlist,
                        onClick = { onWatchlistClick(watchlist.watchlistId, watchlist.watchlistName) }
                    )
                    if (watchlist != watchlists.last())
                        HorizontalDivider()
                }
            }
        }
        is WatchlistUiState.Error -> {
            val message = (uiState as WatchlistUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: $message")
            }
        }
    }
}
