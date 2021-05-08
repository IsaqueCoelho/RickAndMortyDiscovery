package com.studio.sevenapp.domain.di

import com.studio.sevenapp.domain.character.CharacterRepository
import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.domain.character.CharacterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideCharacterUseCase(
        characterRepository: CharacterRepository
    ): CharacterUseCase {
        return CharacterUseCaseImpl(
            characterRepository = characterRepository
        )
    }

    fun provideStringTest(): String{
        return "query 1"
    }

    fun provideStringTest2(): String{
        return "query 1"
    }
}