package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}