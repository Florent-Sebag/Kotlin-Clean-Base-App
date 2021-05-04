package com.sebag.florent.data.repositories.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.User
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserManagerRepositoryImpl
@Inject constructor(
    private val auth: FirebaseAuth
) : UserManagerRepository {

    private var user : FirebaseUser ? = null
    
    override fun getCurrentUser(): Single<FirebaseUser> {
        user?.let {
            return Single.create { emitter ->
                  emitter.onSuccess(it)
            }
        }

        return (Single.create { emitter ->
            user = auth.currentUser
            user?.let {
                emitter.onSuccess(user)
            } ?:run {
                emitter.onError(Throwable("User not connected"))
            }
        });
    }

    override fun isUserConnected(): Boolean {
        return (user != null)
    }

    override fun disconnectUser() {
        user = null
    }

}