package com.studio.sevenapp.rickandmorydiscovery.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PMapperModule {

    @Singleton
    @Provides
    fun providePCharacterInDetailMapper(
        pLocationMapper: PLocationMapper,
        pEpisodeMapper: PEpisodeMapper
    ): PCharacterInDetailMapper {
        return PCharacterInDetailMapper(
            pLocationMapper = pLocationMapper,
            pEpisodeMapper = pEpisodeMapper
        )
    }

    @Singleton
    @Provides
    fun providePLocationMapper(): PLocationMapper {
        return PLocationMapper()
    }

    @Singleton
    @Provides
    fun providePEpisodeMapper(): PEpisodeMapper {
        return PEpisodeMapper()
    }
}