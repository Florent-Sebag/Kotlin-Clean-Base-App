package com.sebag.florent.domain.repositories.auth

import android.content.Intent
import com.facebook.login.widget.LoginButton
import io.reactivex.rxjava3.core.Completable

interface FacebookAuthRepository {

    fun bindFbConnection(btn : LoginButton)

    fun onFbConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) : Completable
}