package com.sebag.florent.presenter.view.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.Joke
import com.sebag.florent.domain.usecases.*
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class HomeVM
@Inject constructor(
    private val sampleUseCase: SampleUseCase,
    private val jokeUseCase: JokeUseCase,
    private val userManagerUseCase: UserManagerUseCase,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    val mJoke = MutableLiveData<Joke>()
    val isDisconnected = MutableLiveData<Boolean>()
    val mEmail = MutableLiveData<String>()

    fun generateJoke() {
        jokeUseCase.getRandomJoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { mJoke.value = it },
                onError = { Log.i("gnah", it.message!!)}
                )
            .addToDisposable()
    }

    fun logoutUser() {
        logoutUseCase.logoutUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { isDisconnected.value = true },
                onError = { Log.i("gnah", it.message!!)}
            )
            .addToDisposable()
    }

    fun getEmail() {
        userManagerUseCase.getCurrentUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { mEmail.value = it.email },
                onError = { mEmail.value = it.message }
            )
            .addToDisposable()
    }
}