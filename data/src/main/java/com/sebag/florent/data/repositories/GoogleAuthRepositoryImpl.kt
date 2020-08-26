package com.sebag.florent.data.repositories

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sebag.florent.data.BuildConfig
import com.sebag.florent.domain.repositories.GoogleAuthRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GoogleAuthRepositoryImpl
@Inject constructor(
    private val auth : FirebaseAuth
) : GoogleAuthRepository {

    val RC_SIGN_IN: Int = 1
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun bindGoogleConnection(activity: Activity) : Intent {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_API_KEY)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity, mGoogleSignInOptions)

        return mGoogleSignInClient.signInIntent
    }

    override fun onGoogleConnectionResult(requestCode: Int, data: Intent?) : Completable {
        return Completable.create { emitter ->
            if (requestCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("gnah", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!, emitter)
                } catch (e: ApiException) {
                    Log.w("gnah", "Google sign in failed", e)
                    emitter.onError(e)
                }
            }
        }.subscribeOn(Schedulers.io())
    }

    private fun firebaseAuthWithGoogle(idToken: String, emitter: CompletableEmitter) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("gnah", "signInWithCredential:success")
                    emitter.onComplete()
                } else {
                    Log.w("gnah", "signInWithCredential:failure", task.exception)
                    emitter.onError(task.exception)
                }
            }
    }
}