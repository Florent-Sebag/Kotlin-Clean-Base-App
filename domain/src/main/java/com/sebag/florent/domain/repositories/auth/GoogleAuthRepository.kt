package com.sebag.florent.domain.repositories.auth

import android.app.Activity
import android.content.Intent
import io.reactivex.rxjava3.core.Completable

interface GoogleAuthRepository {

    fun bindGoogleConnection(activity: Activity) : Intent

    fun onGoogleConnectionResult(requestCode: Int, data: Intent?) : Completable
}