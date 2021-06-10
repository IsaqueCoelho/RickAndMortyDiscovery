package com.studio.sevenapp.rickandmorydiscovery.model

import com.studio.sevenapp.domain.model.StatusEnum

open class PCharacter(
    val id: Int,
    val name: String,
    val status: StatusEnum,
    val image: String
)