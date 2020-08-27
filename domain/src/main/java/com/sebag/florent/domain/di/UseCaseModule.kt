package com.sebag.florent.domain.di

import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.auth.GoogleAuthRepository
import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import com.sebag.florent.domain.repositories.auth.FacebookAuthRepository
import com.sebag.florent.domain.usecases.*
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

    @Provides
    @Singleton
    fun provideAuthUseCase(firebaseAuthRepository: FirebaseAuthRepository,
                           googleAuthRepository: GoogleAuthRepository,
                           facebookAuthRepository: FacebookAuthRepository
    ) : LoginUseCase =
        LoginUseCase(firebaseAuthRepository, googleAuthRepository, facebookAuthRepository)

    @Provides
    @Singleton
    fun provideRegistrationUseCase(firebaseAuthRepository: FirebaseAuthRepository) : RegistrationUseCase =
        RegistrationUseCase(firebaseAuthRepository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(firebaseAuthRepository: FirebaseAuthRepository) : LogoutUseCase =
        LogoutUseCase(firebaseAuthRepository)
}