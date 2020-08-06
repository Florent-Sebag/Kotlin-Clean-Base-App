package com.sebag.florent.presenter.view.fragments.home

import android.util.Log
import com.sebag.florent.presenter.view.base.BaseViewModel
import javax.inject.Inject

class HomeVM @Inject constructor(): BaseViewModel() {

    fun coucou() {
        Log.i("gnah", "PENIS")
    }
}