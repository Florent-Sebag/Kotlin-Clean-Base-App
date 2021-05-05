package com.sebag.florent.domain.models

import android.net.Uri

data class UserModel private constructor(
    val displayName: String,
    val email: String,
    val isEmailVerified: Boolean,
    val phoneNumber: String,
    val photoUrl: Uri
) {
    companion object {
        operator fun invoke(
            displayName: String? = null,
            email: String? = null,
            isEmailVerified: Boolean? = false,
            phoneNumber: String? = null,
            photoUrl: Uri? = null
        ) = UserModel (
            displayName ?: "",
            email ?: "",
            isEmailVerified ?: false,
            phoneNumber ?: "",
            photoUrl ?: Uri.EMPTY
        )
    }
}