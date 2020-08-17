package com.sebag.florent.domain.repositories

import com.sebag.florent.domain.models.User
import io.reactivex.rxjava3.core.Single

interface FirebaseAuthRepository {

    fun loginUser(email: String, password : String) : Single<User>
}