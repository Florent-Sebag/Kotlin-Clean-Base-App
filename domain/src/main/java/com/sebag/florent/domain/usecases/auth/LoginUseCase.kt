package com.sebag.florent.domain.usecases.auth

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.sebag.florent.domain.repositories.auth.FacebookAuthRepository
import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import com.sebag.florent.domain.repositories.auth.GoogleAuthRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val googleAuthRepository: GoogleAuthRepository,
    private val facebookAuthRepository: FacebookAuthRepository
){

    fun logUser(email: String, password: String) : Completable =
        firebaseAuthRepository.loginUser(email, password)

    fun setFbClickListener(fragment: Fragment) {
        facebookAuthRepository.setFbClickListener(fragment)
    }

    fun bindExternalConnection(activity: Activity) : Intent {
        facebookAuthRepository.bindFbConnection()
        return googleAuthRepository.bindGoogleConnection(activity)
    }

    fun onExternalConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) : Completable {
        if (requestCode == 1)
            return googleAuthRepository.onGoogleConnectionResult(requestCode, data)
        return facebookAuthRepository.onFbConnectionResult(requestCode, resultCode, data)
    }
}