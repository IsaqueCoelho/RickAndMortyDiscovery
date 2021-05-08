package com.studio.sevenapp.data.mapper

import com.studio.sevenapp.data.model.CharacterDto
import com.studio.sevenapp.domain.model.Character
import com.studio.sevenapp.domain.model.StatusEnum
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {

    private val characterMapper: CharacterMapper = spyk(
        CharacterMapper()
    )

    @Test
    fun `GIVEN a fake dto character WHEN mapping character THEN assert a domain character`() {
        // Given

        // When
        val domainCharacter =
            characterMapper.mapToDomainModel(dto = FAKE_CHARACTER_DTO)

        // Then
        assertEquals(FAKE_CHARACTER_DOMAIN.id, domainCharacter.id)
        assertEquals(FAKE_CHARACTER_DOMAIN.name, domainCharacter.name)
        assertEquals(FAKE_CHARACTER_DOMAIN.status, domainCharacter.status)
        assertEquals(FAKE_CHARACTER_DOMAIN.image, domainCharacter.image)
    }

    @Test
    fun `GIVEN a fake domain character WHEN mapping character THEN assert a dto character`() {
        // Given

        // When
        val dtoCharacter =
            characterMapper.mapToDto(domainModel = FAKE_CHARACTER_DOMAIN)

        // Then
        assertEquals(FAKE_CHARACTER_DTO.id, dtoCharacter.id)
        assertEquals(FAKE_CHARACTER_DTO.name, dtoCharacter.name)
        assertEquals(FAKE_CHARACTER_DTO.status, dtoCharacter.status)
        assertEquals(FAKE_CHARACTER_DTO.image, dtoCharacter.image)
    }

    companion object {

        val FAKE_CHARACTER_DTO = CharacterDto(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg"
        )

        val FAKE_CHARACTER_DOMAIN = Character(
            id = 361,
            name = "Toxic Rick",
            status = StatusEnum.DEAD,
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg"
        )

    }
}