package com.sebag.florent.domain.di

import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.auth.GoogleAuthRepository
import com.sebag.florent.domain.repositories.JokeRepository
import com.sebag.florent.domain.repositories.SampleRepository
import com.sebag.florent.domain.repositories.auth.FacebookAuthRepository
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import com.sebag.florent.domain.usecases.*
import com.sebag.florent.domain.usecases.auth.LoginUseCase
import com.sebag.florent.domain.usecases.auth.LogoutUseCase
import com.sebag.florent.domain.usecases.auth.RegistrationUseCase
import com.sebag.florent.domain.usecases.auth.UserManagerUseCase
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
        LoginUseCase(
            firebaseAuthRepository,
            googleAuthRepository,
            facebookAuthRepository
        )

    @Provides
    @Singleton
    fun provideRegistrationUseCase(firebaseAuthRepository: FirebaseAuthRepository) : RegistrationUseCase =
        RegistrationUseCase(
            firebaseAuthRepository
        )

    @Provides
    @Singleton
    fun provideLogoutUseCase(firebaseAuthRepository: FirebaseAuthRepository,
                             userManagerRepository: UserManagerRepository
    ) : LogoutUseCase =
        LogoutUseCase(
            firebaseAuthRepository,
            userManagerRepository
        )

    @Provides
    @Singleton
    fun provideUserManagerUseCase(userManagerRepository: UserManagerRepository) : UserManagerUseCase =
        UserManagerUseCase(
            userManagerRepository
        )
}