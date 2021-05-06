package com.sebag.florent.domain.usecases.auth

import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LogoutUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val userManagerRepository: UserManagerRepository
) {

    fun logoutUser() : Completable {
        userManagerRepository.disconnectUser()
        return firebaseAuthRepository.disconnectUser()
    }
}