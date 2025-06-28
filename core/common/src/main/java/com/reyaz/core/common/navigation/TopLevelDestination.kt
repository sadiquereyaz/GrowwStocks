package com.reyaz.core.common.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.reyaz.core.common.R

enum class TopLevelDestination(
    val label: String,
    val route: Route,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int
) {
    HOME(
        unSelectedIcon = R.drawable.home_24px,
        selectedIcon = R.drawable.home_filled_24px,
        label = "Home",
        route = Route.Home
    ),
    WATCHLIST(
        unSelectedIcon = R.drawable.bookmarks_24px,
        selectedIcon = R.drawable.bookmarks_filled_24px,
        label = "Watchlist",
        route = Route.Watchlist
    );

    companion object {
        val START_DESTINATION = HOME

        fun fromNavDestination(destination: NavDestination?): TopLevelDestination {
            return entries.find { dest ->
                destination?.hierarchy?.any { it.hasRoute(dest.route::class) } == true
            } ?: START_DESTINATION
        }

        fun NavDestination.isTopLevel(): Boolean {
            return entries.any {
                hasRoute(it.route::class)
            }
        }
    }
}