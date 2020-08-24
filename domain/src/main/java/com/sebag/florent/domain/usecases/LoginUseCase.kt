package com.sebag.florent.domain.usecases

import android.app.Activity
import android.content.Intent
import com.sebag.florent.domain.repositories.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.GoogleAuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val googleAuthRepository: GoogleAuthRepository
){

    fun logUser(email: String, password: String) : Completable =
        firebaseAuthRepository.loginUser(email, password)

    fun bindGoogleConnection(activity: Activity) : Intent =
        googleAuthRepository.bindGoogleConnection(activity)

    fun onGoogleConnectionResult(requestCode: Int, data: Intent?) : Completable =
        googleAuthRepository.onGoogleConnectionResult(requestCode, data)

    fun isUserConnected() : Boolean = firebaseAuthRepository.isUserConnected()
}