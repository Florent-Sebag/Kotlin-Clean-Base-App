package com.sebag.florent.domain.repositories

import io.reactivex.rxjava3.core.Completable

interface FirebaseAuthRepository {

    fun loginUser(email: String, password : String) : Completable

    fun registerUser(email: String, password : String) : Completable

    fun isUserConnected() : Boolean
}