package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

open class CharacterDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String
)