package com.studio.sevenapp.rickandmorydiscovery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.studio.sevenapp.rickandmorydiscovery.ui.navigation.RickAndMortyNavHost
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.LocalThemeController
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RickAndMortyTheme
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.ThemeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemDark = isSystemInDarkTheme()
            val themeController = remember { ThemeController(systemDark) }

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
