package com.studio.sevenapp.rickandmorydiscovery.ui.list

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.studio.sevenapp.rickandmorydiscovery.ui.components.AnimatedEntrance
import com.studio.sevenapp.rickandmorydiscovery.ui.components.CharacterCard
import com.studio.sevenapp.rickandmorydiscovery.ui.components.LoadingGrid
import com.studio.sevenapp.rickandmorydiscovery.ui.components.PortalRing
import com.studio.sevenapp.rickandmorydiscovery.ui.components.StaggeredVerticalGrid
import com.studio.sevenapp.rickandmorydiscovery.ui.components.ThemeToggleButton
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.LocalThemeController
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmMotion
import com.studio.sevenapp.rickandmorydiscovery.ui.theme.RmTheme

@Composable
fun CharacterListScreen(
    onCharacterClick: (characterId: Int) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characters = viewModel.characterListMs.value
    val themeController = LocalThemeController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RmTheme.colors.background)
    ) {
        ListTopBar(
            isDark = themeController.isDark,
            onToggleTheme = themeController::toggle
        )

        Crossfade(
            targetState = characters.isEmpty(),
            animationSpec = tween(RmMotion.DurationMedium),
            label = "listContent",
            modifier = Modifier.fillMaxSize()
        ) { isLoading ->
            if (isLoading) {
                LoadingGrid(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .navigationBarsPadding()
                )
            } else {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .navigationBarsPadding()
                ) {
                    StaggeredVerticalGrid(
                        maxColumnWidth = 220.dp,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        characters.forEachIndexed { index, character ->
                            AnimatedEntrance(index = index) {
                                CharacterCard(
                                    character = character,
                                    onClick = { onCharacterClick(character.id) }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun ListTopBar(
    isDark: Boolean,
    onToggleTheme: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        PortalRing(
            modifier = Modifier.size(34.dp),
            ringWidth = 4.dp
        )
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Rick & Morty",
                color = RmTheme.colors.onBackground,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Discover the multiverse",
                color = RmTheme.colors.onSurfaceVariant,
                fontSize = 13.sp
            )
        }
        ThemeToggleButton(
            isDark = isDark,
            onToggle = onToggleTheme
        )
    }
}
