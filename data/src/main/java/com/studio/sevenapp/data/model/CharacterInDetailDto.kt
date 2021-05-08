package com.studio.sevenapp.data.model

class CharacterInDetailDto(
    id: Int,
    name: String,
    status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    image: String,
    val episodes: List<EpisodeDto>
) :
    CharacterDto(
        id = id, name = name, status = status, image = image
    )
