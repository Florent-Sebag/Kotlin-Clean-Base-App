package com.sebag.florent.presenter.view.fragments.auth.login

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.usecases.auth.LoginUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class LoginVM
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _mSuccess = MutableLiveData<Boolean>()
    val mSuccess : LiveData<Boolean>
        get() = _mSuccess

    private val _mError = MutableLiveData<String>()
    val mError : LiveData<String>
        get() = _mError

    fun logUser(email: String, password: String) {
        loginUseCase.logUser(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    _mSuccess.value = true
                },
                onError = {
                    _mError.value = it.message
                }
            )
            .addToDisposable()
    }

    fun setFbClickListener(fragment: Fragment) {
        return loginUseCase.setFbClickListener(fragment)
    }

    fun bindExternalConnection(activity: Activity) : Intent {
        return loginUseCase.bindExternalConnection(activity)
    }

    fun onExternalConnectionResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginUseCase.onExternalConnectionResult(requestCode, resultCode, data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    _mSuccess.value = true
                },
                onError = {
                    _mError.value = it.message
                }
            )
            .addToDisposable()
    }
}