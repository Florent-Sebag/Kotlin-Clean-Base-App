package com.sebag.florent.data.repositories.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.UserModel
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserManagerRepositoryImpl
@Inject constructor(
    private val auth: FirebaseAuth
) : UserManagerRepository {

    private var firebaseUser : FirebaseUser ? = null
    private var user : UserModel? = null
    
    override fun getCurrentUser(): Single<UserModel> {
        user?.let {
            return Single.create { emitter ->
                emitter.onSuccess(user)
            }
        }

        return (Single.create { emitter ->
            firebaseUser = auth.currentUser
            firebaseUser?.let {
                user = deserializeFirebaseUser(it)
                emitter.onSuccess(user)
            } ?:run {
                emitter.onError(Throwable("User not connected"))
            }
        });
    }

    override fun isUserConnected(): Boolean {
        // Intentionally blocking for staying connected to the app after closing it
        firebaseUser = auth.currentUser
        return (firebaseUser != null)
    }

    override fun disconnectUser() {
        user = null
        firebaseUser = null
    }

    private fun deserializeFirebaseUser(it: FirebaseUser) : UserModel {
        return (UserModel(it.displayName,
            it.email,
            it.isEmailVerified,
            it.phoneNumber,
            it.photoUrl))
    }

}