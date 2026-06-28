package com.studio.sevenapp.domain.character

import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.domain.model.CharacterPage
import javax.inject.Inject

class CharacterUseCaseImpl
@Inject
constructor(
    private val characterRepository: CharacterRepository
) : CharacterUseCase {

    override suspend fun getCharacters(page: Int): CharacterPage {
        return characterRepository.getCharacters(page)
    }

    override suspend fun getCharacterInDetail(id: Int): CharacterInDetail {
        return characterRepository.getCharacterDetail(id = id)
    }
}