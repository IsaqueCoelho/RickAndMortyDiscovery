package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

open class EpisodeDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("episode") val episodeCode: String,
    @SerializedName("air_date") val airDate: String
)
