package com.studio.sevenapp.data.repository

import com.google.gson.JsonObject
import com.studio.sevenapp.domain.character.BodyQueryEnum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryUtilsModule {

    @Singleton
    @Provides
    fun provideJsonObject(): JsonObject {
        return JsonObject()
    }

    @Singleton
    @Provides
    fun provideRequestType(): MediaType {
        val bodyType = BodyQueryEnum.BODY_TYPE.value
        return bodyType.toMediaType()
    }

    @Singleton
    @Provides
    fun provideRequestBody(
        jsonObject: JsonObject,
        mediaType: MediaType
    ): RequestBody {
        return jsonObject.toString()
            .toRequestBody(mediaType)
    }

}