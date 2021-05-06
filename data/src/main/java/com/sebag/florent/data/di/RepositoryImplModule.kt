package com.sebag.florent.data.di

import com.google.firebase.auth.FirebaseAuth
import com.sebag.florent.data.repositories.JokeRepositoryImpl
import com.sebag.florent.data.repositories.SampleRepositoryImpl
import com.sebag.florent.data.api.IcnDB
import com.sebag.florent.data.repositories.auth.FacebookAuthRepositoryImpl
import com.sebag.florent.data.repositories.auth.FirebaseAuthRepositoryImpl
import com.sebag.florent.data.repositories.auth.GoogleAuthRepositoryImpl
import com.sebag.florent.data.repositories.auth.UserManagerRepositoryImpl
import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.auth.GoogleAuthRepository
import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import com.sebag.florent.domain.repositories.auth.FacebookAuthRepository
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
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

    @Singleton
    @Provides
    fun provideFbAuth(auth: FirebaseAuth) : FacebookAuthRepository =
        FacebookAuthRepositoryImpl(auth)

    @Singleton
    @Provides
    fun provideUserManager(auth: FirebaseAuth) : UserManagerRepository =
        UserManagerRepositoryImpl(auth)
}