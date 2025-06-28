package com.reyaz.core.common.navigation

import android.util.Log
import androidx.navigation.NavController

fun NavController.safeNavigate(route: Route) {
    try {
        navigate(route)
    } catch (e: IllegalArgumentException) {
        Log.e("Navigation", "Failed to navigate to $route", e)
    }
}

fun NavController.bottomNavigationLogic(route: Route) {
    navigate(route = route) {
        popUpTo(Route.Home) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateAndPopUpToStart(route: Route) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

// For clearing back stack and starting fresh
fun NavController.navigateAndClearBackstack(route: Route) {
    navigate(route) {
        popUpTo(0) {
            inclusive = true
        }
    }
}