package com.sebag.florent.domain.repositories.auth

import com.sebag.florent.domain.models.UserModel
import io.reactivex.rxjava3.core.Single

interface UserManagerRepository {

    fun getCurrentUser() : Single<UserModel>

    fun isUserConnected() : Boolean

    fun disconnectUser()
}