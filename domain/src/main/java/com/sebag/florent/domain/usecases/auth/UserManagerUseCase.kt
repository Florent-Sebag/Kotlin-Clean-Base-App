package com.sebag.florent.domain.usecases.auth

import com.sebag.florent.domain.models.UserModel
import com.sebag.florent.domain.repositories.auth.UserManagerRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserManagerUseCase@Inject constructor(
    private val userManagerRepository: UserManagerRepository
) {
    fun isUserConnected() : Boolean = userManagerRepository.isUserConnected()

    fun getCurrentUser() : Single<UserModel> = userManagerRepository.getCurrentUser()
}