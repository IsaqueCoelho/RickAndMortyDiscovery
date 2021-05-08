package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.Character
import com.studio.sevenapp.domain.model.CharacterInDetail

interface CharacterUseCase {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterInDetail(id: Int): CharacterInDetail
}