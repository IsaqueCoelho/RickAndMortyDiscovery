package com.studio.sevenapp.rickandmorydiscovery.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel
@Inject
constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    val characterListMs = mutableStateOf<List<PCharacter>>(listOf())

    init {
        viewModelScope.launch {
            getData()
        }
    }

    private suspend fun getData() {

        val characterList = characterUseCase.getCharacters()
        val characterInDetail = characterUseCase.getCharacterInDetail(characterList.last().id)

        println("last Character name: ${characterInDetail.name}")

        characterListMs.value = characterList.map { character ->
            PCharacter(
                id = character.id,
                name = character.name,
                image = character.image,
                status = character.status
            )
        }

    }
}