package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponseObject(
    @SerializedName("results")
    val results: List<CharacterDto>
)
