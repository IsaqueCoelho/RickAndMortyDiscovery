package com.studio.sevenapp.rickandmorydiscovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studio.sevenapp.data.service.CharacterService
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

    private val countCharacterLv = MutableLiveData<Int>()
    fun getCountCharacter(): LiveData<Int> = countCharacterLv

    fun load() {
        GlobalScope.launch {
            getData()
        }
    }

    private suspend fun getData() {

        val character = characterUseCase.getCharacters()

        countCharacterLv.postValue(
            character.size
        )

    }
}