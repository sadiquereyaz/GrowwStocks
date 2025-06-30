package com.reyaz.core.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.reyaz.core.common.R
import com.reyaz.core.common.model.ThemeMode
import com.reyaz.core.common.navigation.Route
import com.reyaz.core.common.navigation.TopLevelDestination.Companion.isTopLevel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun GrowwTopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    isInWatchlist: Boolean,
    navBackStackEntry: NavBackStackEntry?,
    currentDestination: NavDestination?,
    onSaveClick: () -> Unit,
    toggleTheme: () -> Unit,
    themeMode: ThemeMode,

    onSearchClick: () -> Unit,
    isSearchActive: Boolean,
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String,
    toggleSearchActiveState: () -> Unit,
) {
    val animatedPadding by animateDpAsState(
        targetValue = if (searchQuery.isBlank()) 16.dp else 0.dp,
        label = "SearchBar Horizontal Padding"
    )
    AnimatedContent(
        targetState = isSearchActive,
        transitionSpec = {
            if (targetState) {
                (slideInHorizontally(initialOffsetX = { it }) + fadeIn()).togetherWith(
                    slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
                )
            } else {
                (slideInHorizontally(initialOffsetX = { -it }) + fadeIn()).togetherWith(
                    slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
                )
            }
        },
        label = "TopAppBar Search Animation"
    ) { isSearch ->
        if (isSearch) {
            SearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
//                        modifier = Modifier.padding(horizontal = 60.dp),
                        query = searchQuery,
                        onQueryChange = { onSearchQueryChange(it) },
                        onSearch = {
                            onSearchClick()
                        },
                        expanded = isSearchActive,
                        onExpandedChange = { /*isSearchActive = it*/ },
                        enabled = true,
                        placeholder = { Text("Search stocks...") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    if (searchQuery.isBlank())
                                        toggleSearchActiveState()
                                    else
                                        onSearchQueryChange("")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close search"
                                )
                            }
                        }
                    )
                },
                expanded = searchQuery.isNotBlank(),
                onExpandedChange = {
//                    toggleSearchActiveState()
                    },
                    modifier = modifier
                    .fillMaxWidth()
                        .padding(horizontal = animatedPadding),
                    content = {
                        // Search results content can go here
                        // You can show search suggestions or results
                        Text(
                            text = "Search results will appear here",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )

        } else {
            TopAppBar(
                modifier = modifier,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (currentDestination?.isTopLevel() == true)
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.groww_logo),
                                contentDescription = "Groww Logo",
                                modifier = Modifier.size(32.dp)
                            )
                        Text(
                            text = navBackStackEntry?.arguments?.getString("title")
                                ?: "Groww Stocks",
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
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
                    // Theme switcher
                    IconButton(onClick = { toggleTheme() }) {
                        Icon(
                            imageVector = if (themeMode == ThemeMode.DARK)
                                ImageVector.vectorResource(R.drawable.outline_light_mode_24)
                            else
                                ImageVector.vectorResource(R.drawable.outline_dark_mode_24),
                            contentDescription = "Toggle theme"
                        )
                    }

                    currentDestination?.let { destination ->
                        when {
                            destination.hasRoute(Route.Watchlist::class) -> {
                                IconButton(onClick = { onSaveClick() }) {
                                    Icon(
                                        imageVector = if (isInWatchlist)
                                            ImageVector.vectorResource(R.drawable.bookmark_added_filled_24px)
                                        else
                                            ImageVector.vectorResource(R.drawable.bookmark_add_24px),
                                        contentDescription = "Save to watchlist",
                                        tint = if (isInWatchlist) MaterialTheme.colorScheme.primary else Color.Unspecified
                                    )
                                }
                            }

                            // Search icon - activates search bar
                            destination.hasRoute(Route.Home::class) -> {
                                IconButton(onClick = {
                                    toggleSearchActiveState()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}