package com.sebag.florent.domain.repositories.auth

import io.reactivex.rxjava3.core.Completable

interface FirebaseAuthRepository {

    fun loginUser(email: String, password : String) : Completable

    fun registerUser(email: String, password : String) : Completable

    fun disconnectUser() : Completable

    fun isUserConnected() : Boolean
}