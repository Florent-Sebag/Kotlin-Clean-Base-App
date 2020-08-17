package com.sebag.florent.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebase() : FirebaseAuth = FirebaseAuth.getInstance()
}