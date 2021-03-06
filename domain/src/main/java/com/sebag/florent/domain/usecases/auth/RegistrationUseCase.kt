package com.sebag.florent.domain.usecases.auth

import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RegistrationUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {

    fun registrateUser(email: String, password: String) : Completable {
        return firebaseAuthRepository.registerUser(email, password)
    }
}