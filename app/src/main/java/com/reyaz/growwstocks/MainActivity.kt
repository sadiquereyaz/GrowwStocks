package com.reyaz.growwstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.reyaz.core.ui.theme.GrowwStocksTheme
import com.reyaz.core.common.model.ThemeMode
import com.reyaz.growwstocks.app_bar.presentation.AppBarEvent
import com.reyaz.growwstocks.app_bar.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            !mainViewModel.uiState.value.isThemeLoaded
        }
        setContent {
            val systemInDarkTheme = isSystemInDarkTheme()
            val uiState by mainViewModel.uiState.collectAsState()

            val currentDarkMode = when (uiState.themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> systemInDarkTheme
            }

            SideEffect {
                if (uiState.isThemeLoaded) {
                    WindowCompat.getInsetsController(window, window.decorView).apply {
                        isAppearanceLightStatusBars = !currentDarkMode
                        isAppearanceLightNavigationBars = !currentDarkMode
                    }
                }
            }

            if (uiState.isThemeLoaded) {
                GrowwStocksTheme(
                    darkTheme = currentDarkMode
                ) {
                    GrowwStocksApp(
                        uiState = uiState,
                        onEvent = mainViewModel::onEvent,
                    )
                }
            }
        }
    }
}