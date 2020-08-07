package com.sebag.florent.domain.di

import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import com.sebag.florent.domain.usecases.JokeUseCase
import com.sebag.florent.domain.usecases.SampleUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSampleUseCase(sampleRepository: SampleRepository) : SampleUseCase = SampleUseCase(sampleRepository)

    @Provides
    @Singleton
    fun provideJokeUseCase(jokeRepository: JokeRepository) : JokeUseCase = JokeUseCase(jokeRepository)
}