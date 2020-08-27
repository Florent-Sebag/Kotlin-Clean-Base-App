package com.sebag.florent.data.di

import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.data.repositories.auth.FirebaseAuthRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {
    @Singleton
    @Provides
    fun provideUser(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl) : FirebaseUser {
        return firebaseAuthRepositoryImpl.fetchUser()
    }
}