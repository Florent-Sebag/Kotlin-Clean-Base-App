package com.sebag.florent.data.repositories.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.repositories.auth.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FirebaseAuthRepositoryImpl
@Inject constructor(
    private val auth : FirebaseAuth
) : FirebaseAuthRepository {

    override fun isUserConnected(): Boolean {
        // need to be a blocking function
        // stay connected functionality

        val user = auth.currentUser
        return (user != null)
    }

    override fun loginUser(email: String, password : String): Completable {
        return Completable.create { emitter ->

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(task.exception)
                    }
                }
        }.subscribeOn(Schedulers.io())
    }

    override fun registerUser(email: String, password : String): Completable {
        return Completable.create { emitter ->

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(task.exception)
                    }
                }
        }.subscribeOn(Schedulers.io())
    }

    override fun disconnectUser(): Completable {
        return Completable.create { emitter ->
            auth.signOut()
            emitter.onComplete()
        }
    }

    fun fetchUser() : FirebaseUser {
        return (auth.currentUser!!)
    }
}