package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.Character

interface CharacterUseCase {
    suspend fun getCharacters(): List<Character>
}