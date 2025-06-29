package com.reyaz.growwstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.reyaz.core.ui.theme.GrowwStocksTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        enableEdgeToEdge()

        // todo: implement splash properly
        splashScreen.setKeepOnScreenCondition {
            //splashViewModel.isLoading.value
            false
        }
        setContent {

            // todo: add persistence initial mode manage status and navigation bar color according to theme
            val ise = isSystemInDarkTheme()
            var isDarkMode: Boolean? by rememberSaveable { mutableStateOf(null) }

            GrowwStocksTheme(
                darkTheme = isDarkMode ?: ise
            ) {
                GrowwStocksApp(
                    onDarkModeChange = { isDarkMode = !(isDarkMode ?: ise) }
                )
            }
        }
    }
}