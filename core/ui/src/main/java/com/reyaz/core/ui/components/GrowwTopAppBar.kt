package com.reyaz.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.reyaz.core.common.navigation.Route
import com.reyaz.core.common.navigation.TopLevelDestination.Companion.isTopLevel
import com.reyaz.core.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrowwTopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    isSaved: Boolean,
    navBackStackEntry: NavBackStackEntry?,
    currentDestination: NavDestination?,
    onSaveClick: () -> Unit,
    onSearchClick: () -> Unit,
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.groww_logo),
                    contentDescription = "Groww Logo",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = navBackStackEntry?.arguments?.getString("title") ?: "Groww Stocks",
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        navigationIcon = {
            if (currentDestination != null && navController.previousBackStackEntry != null && !currentDestination.isTopLevel()) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            currentDestination?.let { destination ->
                when {
                    destination.hasRoute(Route.Watchlist::class) -> {
                        IconButton(onClick = { onSaveClick() }) {
                            Icon(
                                imageVector = if (isSaved) ImageVector.vectorResource(R.drawable.bookmark_added_filled_24px) else ImageVector.vectorResource(
                                    R.drawable.bookmark_add_24px
                                ),
                                contentDescription = "save",
                                tint = if (isSaved) MaterialTheme.colorScheme.primary else Color.Unspecified
                            )
                        }
                    }

                    destination.hasRoute(Route.Home::class) -> {
                        IconButton(onClick = { onSearchClick() }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search",
//                            modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    )
}