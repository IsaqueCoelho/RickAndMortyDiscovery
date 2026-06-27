package com.studio.sevenapp.rickandmorydiscovery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.studio.sevenapp.rickandmorydiscovery.ui.navigation.RickAndMortyNavHost
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RickAndMoryDiscoveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMoryDiscoveryTheme {
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
