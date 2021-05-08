package com.studio.sevenapp.data.mapper

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideCharacterMapper(
        gson: Gson
    ): CharacterMapper {
        return CharacterMapper(gson = gson)
    }

    @Singleton
    @Provides
    fun provideCharacterInDetailMapper(
        locationMapper: LocationMapper,
        episodeMapper: EpisodeMapper,
        gson: Gson
    ): CharacterInDetailMapper {
        return CharacterInDetailMapper(
            locationMapper = locationMapper,
            episodeMapper = episodeMapper,
            gson = gson
        )
    }

    @Singleton
    @Provides
    fun provideLocationMapper(): LocationMapper {
        return LocationMapper()
    }

    @Singleton
    @Provides
    fun provideEpisodeMapper(): EpisodeMapper {
        return EpisodeMapper()
    }

}