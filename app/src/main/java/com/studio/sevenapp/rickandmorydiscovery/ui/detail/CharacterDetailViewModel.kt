package com.studio.sevenapp.rickandmorydiscovery.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.rickandmorydiscovery.mapper.PCharacterInDetailMapper
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacterInDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel
@Inject
constructor(
    private val characterUseCase: CharacterUseCase,
    private val pCharacterInDetailMapper: PCharacterInDetailMapper
) : ViewModel() {

    val characterMs = mutableStateOf<PCharacterInDetail?>(null)

    /** Transient user-facing message (e.g. shown when the API rate-limits us). */
    val userMessage = mutableStateOf<String?>(null)

    fun clearMessage() {
        userMessage.value = null
    }

    fun requestCharacterDetails(characterId: Int?) {
        characterId ?: return

        viewModelScope.launch {
            try {
                val characterInDetail = characterUseCase.getCharacterInDetail(characterId)
                characterMs.value = pCharacterInDetailMapper.mapToPresentationModel(characterInDetail)
            } catch (throwable: Throwable) {
                userMessage.value = "Espere um pouco para carregar mais informações"
            }
        }
    }
}