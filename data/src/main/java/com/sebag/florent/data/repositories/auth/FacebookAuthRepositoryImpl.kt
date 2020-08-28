package com.sebag.florent.data.repositories.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
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

    private lateinit var callbackManager : CallbackManager
    private lateinit var completable: Completable
    private lateinit var loginManager: LoginManager

    override fun setFbClickListener(fragment: Fragment) {
        loginManager.logInWithReadPermissions(fragment, listOf("email"))
    }

    override fun bindFbConnection() {
        loginManager = LoginManager.getInstance()
        callbackManager = CallbackManager.Factory.create()
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.i("gnah", "facebook:onSuccess:$loginResult")
                logToFirebase(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.i("gnah", "facebook:onCancel")
                setErrorCompletable("canceled")
            }

            override fun onError(error: FacebookException) {
                Log.i("gnah", "facebook:onError", error)
                setErrorCompletable("error")
            }
        })
    }

    private fun logToFirebase(token: AccessToken) {
        completable = Completable.create {
            val credential = FacebookAuthProvider.getCredential(token.token)
            this.signInFirebaseWithCredentials(auth, credential, it)
        }.subscribeOn(Schedulers.io())
    }

    private fun setErrorCompletable(msg : String) {
        completable = Completable.create {
            it.onError(Throwable("Facebook connection $msg"))
        }
    }

    override fun onFbConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) : Completable {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        return (completable)
    }

}