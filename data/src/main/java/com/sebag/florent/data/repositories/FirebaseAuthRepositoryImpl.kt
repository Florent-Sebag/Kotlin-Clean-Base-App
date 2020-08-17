package com.sebag.florent.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.User
import com.sebag.florent.domain.repositories.FirebaseAuthRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FirebaseAuthRepositoryImpl
@Inject constructor(
    private val auth : FirebaseAuth
) : FirebaseAuthRepository {

    override fun loginUser(email: String, password : String): Single<User> {
        return Single.create<FirebaseUser> { emitter ->

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        emitter.onSuccess(
                            user
                        )
                    } else {
                        emitter.onError(task.exception)
                    }
                }
        }.subscribeOn(Schedulers.io()).map {user ->
            //????
            User(
                user!!.displayName,
                user.email,
                user.photoUrl,
                user.isEmailVerified,
                user.uid
            )
        }

    }

}