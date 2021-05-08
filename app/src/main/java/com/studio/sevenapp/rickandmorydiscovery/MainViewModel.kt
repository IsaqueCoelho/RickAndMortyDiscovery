package com.studio.sevenapp.rickandmorydiscovery

import androidx.lifecycle.ViewModel
import com.studio.sevenapp.domain.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    fun load() {
        GlobalScope.launch {
            getData()
        }
    }

    private suspend fun getData() {

        val characterList = characterUseCase.getCharacters()
        val characterInDetail = characterUseCase.getCharacterInDetail(characterList.last().id)

        println("last Character name: ${characterInDetail.name}")

    }
}