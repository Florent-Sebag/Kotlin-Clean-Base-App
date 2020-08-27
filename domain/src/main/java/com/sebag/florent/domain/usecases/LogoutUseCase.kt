package com.sebag.florent.domain.usecases

import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LogoutUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {

    fun logoutUser() : Completable {
        return firebaseAuthRepository.disconnectUser()
    }
}