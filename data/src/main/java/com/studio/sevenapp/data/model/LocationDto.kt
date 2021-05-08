package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String
)
