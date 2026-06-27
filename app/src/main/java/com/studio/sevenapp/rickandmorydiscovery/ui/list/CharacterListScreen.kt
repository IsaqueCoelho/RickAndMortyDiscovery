package com.studio.sevenapp.rickandmorydiscovery.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.studio.sevenapp.rickandmorydiscovery.ui.components.CharacterCard
import com.studio.sevenapp.rickandmorydiscovery.ui.components.StaggeredVerticalGrid

@Composable
fun CharacterListScreen(
    onCharacterClick: (characterId: Int) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val characters = viewModel.characterListMs.value

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            characters.forEach { character ->
                CharacterCard(
                    character = character,
                    onClick = { onCharacterClick(character.id) }
                )
            }
        }
    }
}
