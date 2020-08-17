package com.sebag.florent.domain.models

import android.net.Uri

data class User(
    val name: String?,
    val email : String?,
    val photoUrl : Uri?,
    val emailVerified : Boolean?,
    val uid : String?
)