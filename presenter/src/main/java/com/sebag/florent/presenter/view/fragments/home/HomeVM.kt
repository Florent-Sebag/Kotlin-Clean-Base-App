package com.sebag.florent.presenter.view.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.models.JokeModel
import com.sebag.florent.domain.usecases.*
import com.sebag.florent.domain.usecases.auth.LogoutUseCase
import com.sebag.florent.domain.usecases.auth.UserManagerUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class HomeVM
@Inject constructor(
    private val jokeUseCase: JokeUseCase,
    private val userManagerUseCase: UserManagerUseCase,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    private val _mJoke = MutableLiveData<JokeModel>()
    val mJoke : LiveData<JokeModel> = _mJoke

    private val _isDisconnected = MutableLiveData<Boolean>()
    val isDisconnected : LiveData<Boolean> = _isDisconnected

    private val _mEmail = MutableLiveData<String>()
    val mEmail : LiveData<String> = _mEmail

    fun generateJoke() {
        jokeUseCase.getRandomJoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _mJoke.value = it },
                onError = { Log.i("gnah", it.message!!)}
                )
            .addToDisposable()
    }

    fun logoutUser() {
        logoutUseCase.logoutUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { _isDisconnected.value = true },
                onError = { Log.i("gnah", it.message!!)}
            )
            .addToDisposable()
    }

    fun getEmail() {
        userManagerUseCase.getCurrentUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _mEmail.value = it.email },
                onError = { _mEmail.value = it.message }
            )
            .addToDisposable()
    }
}