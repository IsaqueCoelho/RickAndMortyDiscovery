package com.studio.sevenapp.data.mapper

import com.studio.sevenapp.data.model.CharacterInDetailDto
import com.studio.sevenapp.data.model.EpisodeDto
import com.studio.sevenapp.data.model.LocationDto
import com.studio.sevenapp.domain.model.*
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterInDetailMapperTest {

    private lateinit var locationMapper: LocationMapper

    private lateinit var episodeMapper: EpisodeMapper

    private lateinit var characterInDetailMapper: CharacterInDetailMapper

    @Before
    fun onSetup() {

        locationMapper = LocationMapper()

        episodeMapper = EpisodeMapper()

        characterInDetailMapper = spyk(
            CharacterInDetailMapper(
                locationMapper = locationMapper,
                episodeMapper = episodeMapper
            )
        )
    }

    @Test
    fun `GIVEN a fake dto character WHEN mapping character THEN assert a domain character`() {
        // Given

        // When
        val domainCharacter = characterInDetailMapper.mapToDomainModel(dto = FAKE_CHARACTER_DTO)

        // Then
        assertEquals(FAKE_CHARACTER_DOMAIN.id, domainCharacter.id)
        assertEquals(FAKE_CHARACTER_DOMAIN.name, domainCharacter.name)
        assertEquals(FAKE_CHARACTER_DOMAIN.status, domainCharacter.status)
        assertEquals(FAKE_CHARACTER_DOMAIN.species, domainCharacter.species)
        assertEquals(FAKE_CHARACTER_DOMAIN.type, domainCharacter.type)
        assertEquals(FAKE_CHARACTER_DOMAIN.gender, domainCharacter.gender)
        assertEquals(FAKE_CHARACTER_DOMAIN.origin, domainCharacter.origin)
        assertEquals(FAKE_CHARACTER_DOMAIN.location, domainCharacter.location)
        assertEquals(FAKE_CHARACTER_DOMAIN.image, domainCharacter.image)
        assertEquals(FAKE_CHARACTER_DOMAIN.episodes[0].id, domainCharacter.episodes[0].id)
        assertEquals(FAKE_CHARACTER_DOMAIN.episodes[0].name, domainCharacter.episodes[0].name)
        assertEquals(FAKE_CHARACTER_DOMAIN.episodes[0].episodeCode, domainCharacter.episodes[0].episodeCode)
    }

    @Test
    fun `GIVEN a fake domain character WHEN mapping character THEN assert a dto character`() {
        // Given

        // When
        val dtoCharacter = characterInDetailMapper.mapToDto(domainModel = FAKE_CHARACTER_DOMAIN)

        // Then
        assertEquals(FAKE_CHARACTER_DTO.id, dtoCharacter.id)
        assertEquals(FAKE_CHARACTER_DTO.name, dtoCharacter.name)
        assertEquals(FAKE_CHARACTER_DTO.status, dtoCharacter.status)
        assertEquals(FAKE_CHARACTER_DTO.species, dtoCharacter.species)
        assertEquals(FAKE_CHARACTER_DTO.type, dtoCharacter.type)
        assertEquals(FAKE_CHARACTER_DTO.gender, dtoCharacter.gender)
        assertEquals(FAKE_CHARACTER_DTO.origin, dtoCharacter.origin)
        assertEquals(FAKE_CHARACTER_DTO.location, dtoCharacter.location)
        assertEquals(FAKE_CHARACTER_DTO.image, dtoCharacter.image)
        assertEquals(FAKE_CHARACTER_DTO.episodes[0].id, dtoCharacter.episodes[0].id)
        assertEquals(FAKE_CHARACTER_DTO.episodes[0].name, dtoCharacter.episodes[0].name)
        assertEquals(FAKE_CHARACTER_DTO.episodes[0].episodeCode, dtoCharacter.episodes[0].episodeCode)
    }

    companion object {

        val FAKE_LOCATION_DTO: LocationDto = LocationDto(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "Dimension C-137"
        )

        val FAKE_LOCATION_DOMAIN: Location = Location(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "Dimension C-137"
        )

        val FAKE_EPISODE_DTO = EpisodeDto(
            id = 1,
            name = "Pilot",
            episodeCode = "S0101"
        )

        val FAKE_EPISODE_DOMAIN = Episode(
            id = 1,
            name = "Pilot",
            episodeCode = "S0101"
        )

        val FAKE_EPISODE_DTO_LIST = listOf<EpisodeDto>(
            FAKE_EPISODE_DTO
        )

        val FAKE_EPISODE_DOMAIN_LIST = listOf<Episode>(
            FAKE_EPISODE_DOMAIN
        )

        val FAKE_CHARACTER_DTO = CharacterInDetailDto(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            type = "Rick's Toxic Side",
            gender = "Male",
            origin = FAKE_LOCATION_DTO,
            location = FAKE_LOCATION_DTO,
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            episodes = FAKE_EPISODE_DTO_LIST
        )

        val FAKE_CHARACTER_DOMAIN = CharacterInDetail(
            id = 361,
            name = "Toxic Rick",
            status = StatusEnum.DEAD,
            species = "Humanoid",
            type = "Rick's Toxic Side",
            gender = GenderEnum.MALE,
            origin = FAKE_LOCATION_DOMAIN,
            location = FAKE_LOCATION_DOMAIN,
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            episodes = FAKE_EPISODE_DOMAIN_LIST
        )

    }

}