package com.sebag.florent.domain.usecases

import com.sebag.florent.domain.repositories.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
){

    fun logUser(email: String, password: String) : Completable =
        firebaseAuthRepository.loginUser(email, password)

    fun isUserConnected() : Boolean = firebaseAuthRepository.isUserConnected()
}