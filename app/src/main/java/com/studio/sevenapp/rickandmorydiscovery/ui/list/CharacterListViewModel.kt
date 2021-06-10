package com.studio.sevenapp.rickandmorydiscovery.ui.list

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
            getCharacterList()
        }
    }

    private suspend fun getCharacterList() {

        val characterList = characterUseCase.getCharacters()

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