package com.reyaz.growwstocks

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.reyaz.growwstocks.navigation.AppNavHost

@Composable
fun GrowwStocksApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { /*GrowwTopAppBar()*/ },
        bottomBar = { /*GrowwBottomNavBar(navController) */}
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
