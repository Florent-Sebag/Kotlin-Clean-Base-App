package com.sebag.florent.data.di

import com.sebag.florent.data.SampleRepositoryImpl
import com.sebag.florent.domain.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryImplModule {

    @Singleton
    @Provides
    fun provideSampleRepositoryImpl() : SampleRepository {
        return SampleRepositoryImpl()
    }
}