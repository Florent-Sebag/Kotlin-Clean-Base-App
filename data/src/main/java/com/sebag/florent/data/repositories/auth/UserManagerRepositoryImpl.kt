package com.sebag.florent.data.repositories.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.User
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
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
        // Intentionally blocking for staying connected to the app after closing it
        user = auth.currentUser
        return (user != null)
    }

    override fun disconnectUser() {
        user = null
    }

}