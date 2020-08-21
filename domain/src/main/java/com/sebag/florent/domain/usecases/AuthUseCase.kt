package com.sebag.florent.domain.usecases

import com.sebag.florent.domain.models.User
import com.sebag.florent.domain.repositories.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
){

    fun logUser(email: String, password: String) : Single<User> =
        firebaseAuthRepository.loginUser(email, password)

    fun isUserConnected() : Boolean = firebaseAuthRepository.isUserConnected()
}