package com.studio.sevenapp.domain.di

import com.studio.sevenapp.domain.character.CharacterUseCase
import com.studio.sevenapp.domain.character.CharacterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindCharacterUseCase(
        characterUseCaseImpl: CharacterUseCaseImpl
    ): CharacterUseCase

}