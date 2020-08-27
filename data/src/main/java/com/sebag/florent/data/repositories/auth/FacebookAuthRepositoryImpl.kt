package com.sebag.florent.data.repositories.auth

import android.content.Intent
import android.util.Log
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.sebag.florent.domain.repositories.auth.FacebookAuthRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FacebookAuthRepositoryImpl
@Inject constructor(
    private val auth : FirebaseAuth
) : BaseExternalAuthRepositoryImpl(), FacebookAuthRepository {

    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var completable: Completable

    override fun bindFbConnection(btn : LoginButton) {
            btn.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.i("gnah", "facebook:onSuccess:$loginResult")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.i("gnah", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.i("gnah", "facebook:onError", error)
                }
            })

    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        completable = Completable.create {
            val credential = FacebookAuthProvider.getCredential(token.token)
            this.signInFirebaseWithCredentials(auth, credential, it)
        }.subscribeOn(Schedulers.io())
    }

    override fun onFbConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) : Completable {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        return (completable)
    }

}