package com.sebag.florent.presenter.view.fragments.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sebag.florent.domain.usecases.auth.RegistrationUseCase
import com.sebag.florent.presenter.view.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class RegistrationVM
@Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : BaseViewModel() {

    private val _mSuccess = MutableLiveData<Boolean>()
    val mSuccess : LiveData<Boolean> = _mSuccess

    private val _mError = MutableLiveData<String>()
    val mError : LiveData<String> = _mError

    fun registrateUser(email: String, password: String) {
        registrationUseCase.registrateUser(email, password)
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