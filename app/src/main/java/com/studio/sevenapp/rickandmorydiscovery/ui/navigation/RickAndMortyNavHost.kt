package com.studio.sevenapp.rickandmorydiscovery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.studio.sevenapp.rickandmorydiscovery.ui.detail.CharacterDetailScreen
import com.studio.sevenapp.rickandmorydiscovery.ui.list.CharacterListScreen

@Composable
fun RickAndMortyNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CharacterListRoute
    ) {
        composable<CharacterListRoute> {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(CharacterDetailRoute(characterId))
                }
            )
        }

        composable<CharacterDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<CharacterDetailRoute>()
            CharacterDetailScreen(
                characterId = route.characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
