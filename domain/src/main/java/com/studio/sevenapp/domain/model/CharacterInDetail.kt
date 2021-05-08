package com.studio.sevenapp.domain.model

class CharacterInDetail(
    id: Int,
    name: String,
    status: StatusEnum,
    val species: String,
    val type: String,
    val gender: GenderEnum,
    val origin: Location,
    val location: Location,
    image: String,
    val episodes: List<Episode>
) : Character(
    id = id, name = name, status = status, image = image
)
