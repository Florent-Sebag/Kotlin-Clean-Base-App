package com.sebag.florent.data.repositories.auth

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.CompletableEmitter

abstract class BaseExternalAuthRepositoryImpl {

    protected fun signInFirebaseWithCredentials(auth: FirebaseAuth, credential: AuthCredential,
                                      emitter: CompletableEmitter) {
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