package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel
@Inject
constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    val characterMs = mutableStateOf<List<PCharacter>>(listOf())

    fun requestCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            getCharacterDetails(characterId)
        }
    }

    private suspend fun getCharacterDetails(characterId: Int) {

        val characterInDetail = characterUseCase
            .getCharacterInDetail(characterId)

    }
}