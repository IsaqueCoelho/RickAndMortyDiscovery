package com.studio.sevenapp.rickandmorydiscovery.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.studio.sevenapp.rickandmorydiscovery.ui.detail.CharacterDetailScreen
import com.studio.sevenapp.rickandmorydiscovery.ui.list.CharacterListScreen
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion

private const val SlideDuration = 420

@Composable
fun RickAndMortyNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CharacterListRoute
    ) {
        composable<CharacterListRoute>(
            enterTransition = { fadeIn(tween(RmMotion.DurationMedium)) },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(SlideDuration, easing = RmMotion.Emphasized),
                    targetOffsetX = { -it / 4 }
                ) + fadeOut(tween(RmMotion.DurationMedium))
            },
            popEnterTransition = {
                slideInHorizontally(
                    animationSpec = tween(SlideDuration, easing = RmMotion.Emphasized),
                    initialOffsetX = { -it / 4 }
                ) + fadeIn(tween(RmMotion.DurationMedium))
            }
        ) {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(CharacterDetailRoute(characterId))
                }
            )
        }

        composable<CharacterDetailRoute>(
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(SlideDuration, easing = RmMotion.Emphasized),
                    initialOffsetX = { fullWidth -> fullWidth }
                ) + fadeIn(tween(RmMotion.DurationMedium))
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(SlideDuration, easing = RmMotion.Emphasized),
                    targetOffsetX = { fullWidth -> fullWidth }
                ) + fadeOut(tween(RmMotion.DurationMedium))
            }
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<CharacterDetailRoute>()
            CharacterDetailScreen(
                characterId = route.characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
