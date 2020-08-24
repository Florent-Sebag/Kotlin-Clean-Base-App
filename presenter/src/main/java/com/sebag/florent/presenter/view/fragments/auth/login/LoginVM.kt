package com.sebag.florent.presenter.view.fragments.auth.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.usecases.LoginUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class LoginVM
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val mSuccess = MutableLiveData<Boolean>()
    val mError = MutableLiveData<String>()

    fun logUser(email: String, password: String) {
        loginUseCase.logUser(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    mSuccess.value = true
                },
                onError = {
                    mError.value = it.message
                }
            )
            .addToDisposable()
    }

    fun bindGoogleConnection(activity: Activity) : Intent {
        return loginUseCase.bindGoogleConnection(activity)
    }

    fun onGoogleConnectionResult(requestCode: Int, data: Intent?) {
        loginUseCase.onGoogleConnectionResult(requestCode, data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    mSuccess.value = true
                },
                onError = {
                    mError.value = it.message
                }
            )
            .addToDisposable()
    }
}