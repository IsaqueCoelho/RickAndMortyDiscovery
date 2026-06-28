package com.studio.sevenapp.data.model

import com.google.gson.annotations.SerializedName

/** Pagination metadata returned by the GraphQL `characters` query's `info` block. */
data class InfoDto(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("pages") val pages: Int = 0,
    @SerializedName("next") val next: Int? = null,
    @SerializedName("prev") val prev: Int? = null
)
