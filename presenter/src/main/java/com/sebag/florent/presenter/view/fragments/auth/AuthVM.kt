package com.sebag.florent.presenter.view.fragments.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.models.Joke
import com.sebag.florent.domain.models.User
import com.sebag.florent.domain.usecases.AuthUseCase
import com.sebag.florent.domain.usecases.SampleUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class AuthVM @Inject constructor(private val authUseCase: AuthUseCase) : BaseViewModel() {

    val mUser = MutableLiveData<User>()
    val mError = MutableLiveData<String>()

    fun logUser(email: String, password: String) {
        authUseCase.logUser(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { it ->
                    mUser.value = it
                },
                onError = {
                    mError.value = it.message
                }
            )
            .addToDisposable()
    }
}