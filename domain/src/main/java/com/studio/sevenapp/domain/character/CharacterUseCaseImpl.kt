package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterUseCaseImpl
@Inject
constructor(
    private val characterRepository: CharacterRepository
) : CharacterUseCase {
    override suspend fun getCharacters(): List<Character> {
        return characterRepository.getCharacters()
    }
}