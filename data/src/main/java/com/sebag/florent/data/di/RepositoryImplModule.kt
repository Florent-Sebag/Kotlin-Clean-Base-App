package com.sebag.florent.data.di

import com.google.firebase.auth.FirebaseAuth
import com.sebag.florent.data.repositories.JokeRepositoryImpl
import com.sebag.florent.data.repositories.SampleRepositoryImpl
import com.sebag.florent.data.api.IcnDB
import com.sebag.florent.data.repositories.FirebaseAuthRepositoryImpl
import com.sebag.florent.data.repositories.GoogleAuthRepositoryImpl
import com.sebag.florent.domain.repositories.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.GoogleAuthRepository
import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryImplModule {

    @Singleton
    @Provides
    fun provideSampleRepositoryImpl() : SampleRepository =
        SampleRepositoryImpl()

    @Singleton
    @Provides
    fun provideJokeRepositoryImpl(service: IcnDB) : JokeRepository =
        JokeRepositoryImpl(service)

    @Singleton
    @Provides
    fun provideAuthRepositoryImpl(auth: FirebaseAuth) : FirebaseAuthRepository =
        FirebaseAuthRepositoryImpl(auth)

    @Singleton
    @Provides
    fun provideGoogleAuth(auth: FirebaseAuth) : GoogleAuthRepository =
        GoogleAuthRepositoryImpl(auth)
}