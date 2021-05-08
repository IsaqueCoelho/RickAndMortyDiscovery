package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

class CharacterInDetailDto(
    id: Int,
    name: String,
    status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: LocationDto,
    @SerializedName("location") val location: LocationDto,
    image: String,
    @SerializedName("episode") val episodes: List<EpisodeDto>
) :
    CharacterDto(
        id = id, name = name, status = status, image = image
    )
