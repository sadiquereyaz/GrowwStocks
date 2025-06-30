package com.reyaz.growwstocks.app_bar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reyaz.core.database.entity.watchlist.WatchlistWithStockPresence

@Composable
fun WatchlistSelectionBottomSheet(
    watchlists: List<WatchlistWithStockPresence>,
    onToggleWatchlistChecked: (Long, Boolean) -> Unit,
    onAddNewWatchlist: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newWatchlistName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Add to Watchlists",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Outlined Text Field + Add Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = newWatchlistName,
                onValueChange = { newWatchlistName = it },
                label = { Text("New Watchlist Name") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Button(
                onClick = {
                    if (newWatchlistName.isNotBlank()) {
                        onAddNewWatchlist(newWatchlistName.trim())
                        newWatchlistName = ""
                    }
                },
                enabled = newWatchlistName.isNotBlank()
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(watchlists) { watchlist ->
                WatchlistSelectionItem(
                    watchlist = watchlist,
                    onCheckedChange = { isChecked ->
                        onToggleWatchlistChecked(watchlist.watchlistId, isChecked)
                    }
                )
            }
        }
    }
}
