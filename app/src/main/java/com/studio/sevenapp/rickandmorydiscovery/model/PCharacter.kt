package com.studio.sevenapp.rickandmorydiscovery.model

import com.studio.sevenapp.domain.model.StatusEnum

data class PCharacter(
    val id: Int,
    val name: String,
    val status: StatusEnum,
    val image: String
)