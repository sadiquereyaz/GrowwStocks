package com.reyaz.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.reyaz.core.common.navigation.TopLevelDestination
import com.reyaz.core.common.navigation.bottomNavigationLogic


@Composable
fun GrowwBottomNavBar(
    navController: NavController,
    currentDestination: NavDestination?
) {
    val topLevelDestination = TopLevelDestination.fromNavDestination(currentDestination)

    NavigationBar(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        TopLevelDestination.entries.forEach { topLevelRoute ->
            val isSelected = topLevelRoute == topLevelDestination
            NavigationBarItem(
                icon = {
                    val iconId: Int = if (isSelected)
                        topLevelRoute.selectedIcon else topLevelRoute.unSelectedIcon
                    Icon(
                        ImageVector.vectorResource(iconId),
                        contentDescription = topLevelRoute.label,
                        modifier = Modifier.size(24.dp),
                    )
                },
                alwaysShowLabel = false,
                label = {
                    Text(topLevelRoute.label)
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.bottomNavigationLogic(topLevelRoute.route)
                    }
                }
            )
        }
    }
}