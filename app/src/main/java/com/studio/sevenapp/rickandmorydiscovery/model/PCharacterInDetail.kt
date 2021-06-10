package com.studio.sevenapp.rickandmorydiscovery.model

import com.studio.sevenapp.domain.model.GenderEnum
import com.studio.sevenapp.domain.model.StatusEnum

class PCharacterInDetail(
    id: Int = 0,
    name: String = "",
    status: StatusEnum = StatusEnum.UNKNOWN,
    val species: String = "",
    val type: String = "",
    val gender: GenderEnum = GenderEnum.UNKNOWN,
    val origin: PLocation = PLocation(),
    val location: PLocation = PLocation(),
    image: String = "",
    val episodes: List<PEpisode> = listOf()
) : PCharacter(
    id = id, name = name, status = status, image = image
)