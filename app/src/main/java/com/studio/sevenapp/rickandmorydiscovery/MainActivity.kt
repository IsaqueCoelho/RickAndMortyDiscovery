package com.studio.sevenapp.rickandmorydiscovery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.studio.sevenapp.rickandmorydiscovery.ui.navigation.RickAndMortyNavHost
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.LocalThemeController
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RickAndMortyTheme
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.ThemeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Draw behind the system bars; the bars become transparent and content goes edge-to-edge.
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            val themeController = remember { ThemeController(systemDark) }

            // Keep the status/navigation bar icon contrast in sync with the in-app theme toggle,
            // which is independent of the system setting.
            val view = LocalView.current
            SideEffect {
                val barAppearance = WindowCompat.getInsetsController(window, view)
                barAppearance.isAppearanceLightStatusBars = !themeController.isDark
                barAppearance.isAppearanceLightNavigationBars = !themeController.isDark
            }

            CompositionLocalProvider(LocalThemeController provides themeController) {
                RickAndMortyTheme(darkTheme = themeController.isDark) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        RickAndMortyNavHost()
                    }
                }
            }
        }
    }
}
