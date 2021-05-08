package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.Character
import com.studio.sevenapp.domain.model.CharacterInDetail

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterDetail(id: Int): CharacterInDetail
}