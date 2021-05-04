package com.sebag.florent.domain.repositories.auth

import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single

interface UserManagerRepository {

    //FirebaseUser ?
    fun getCurrentUser() : Single<FirebaseUser>

    fun isUserConnected() : Boolean

    fun disconnectUser()
}