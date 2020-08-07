package com.sebag.florent.presenter.view.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.models.Joke
import com.sebag.florent.domain.usecases.JokeUseCase
import com.sebag.florent.domain.usecases.SampleUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeVM
@Inject constructor(
    private val sampleUseCase: SampleUseCase,
    private val jokeUseCase: JokeUseCase
): BaseViewModel() {

    val mJoke = MutableLiveData<Joke>()

    fun generateJoke() {
        jokeUseCase.getRandomJoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { mJoke.value = it },
                onError = { Log.i("gnah", it.message!!)}
                )
            .addToDisposable()
    }
}