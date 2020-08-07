package com.sebag.florent.presenter.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    //créer dans add? possible?
    protected var compositeDisposable = CompositeDisposable()
    //protected lateinit var compositeDisposable : CompositeDisposable

    protected fun Disposable.addToDisposable() {
//        if (!::compositeDisposable.isInitialized)
//            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}