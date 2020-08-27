package com.sebag.florent.presenter.view.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.sebag.florent.domain.models.Joke
import com.sebag.florent.domain.usecases.JokeUseCase
import com.sebag.florent.domain.usecases.LogoutUseCase
import com.sebag.florent.domain.usecases.SampleUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class HomeVM
@Inject constructor(
    private val sampleUseCase: SampleUseCase,
    private val jokeUseCase: JokeUseCase,
    private val user: FirebaseUser,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    val mJoke = MutableLiveData<Joke>()
    val isDisconnected = MutableLiveData<Boolean>()

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

    fun getEmail() : String? {
        return (user.email)
    }
}