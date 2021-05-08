package com.studio.sevenapp.data.service

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CharacterService {
    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun get(@Body body: RequestBody): String
}