package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.domain.model.CharacterPage

interface CharacterUseCase {
    suspend fun getCharacters(page: Int): CharacterPage
    suspend fun getCharacterInDetail(id: Int): CharacterInDetail
}