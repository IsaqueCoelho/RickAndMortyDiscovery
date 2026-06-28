package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.domain.model.CharacterPage

interface CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterPage
    suspend fun getCharacterDetail(id: Int): CharacterInDetail
}