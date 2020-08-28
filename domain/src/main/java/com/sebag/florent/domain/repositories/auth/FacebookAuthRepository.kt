package com.sebag.florent.domain.repositories.auth

import android.content.Intent
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Completable

interface FacebookAuthRepository {

    fun setFbClickListener(fragment: Fragment)

    fun bindFbConnection()

    fun onFbConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) : Completable
}