package com.sebag.florent.presenter.view.fragments.detail

import android.util.Log
import com.sebag.florent.domain.usecases.SampleUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import javax.inject.Inject

class AuthVM @Inject constructor(private val sampleUseCase: SampleUseCase) : BaseViewModel() {

    fun coucou() {

        Log.i("gnah", sampleUseCase.fetchSampleData().toString())
    }
}