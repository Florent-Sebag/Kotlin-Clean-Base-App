package com.sebag.florent.data.di

import com.sebag.florent.data.JokeRepositoryImpl
import com.sebag.florent.data.SampleRepositoryImpl
import com.sebag.florent.data.api.IcnDB
import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryImplModule {

    @Singleton
    @Provides
    fun provideSampleRepositoryImpl() : SampleRepository = SampleRepositoryImpl()

    @Singleton
    @Provides
    fun provideJokeRepositoryImpl(service: IcnDB) : JokeRepository = JokeRepositoryImpl(service)
}