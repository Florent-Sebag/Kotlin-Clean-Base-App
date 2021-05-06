package com.sebag.florent.presenter.view.fragments.auth.registration

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

    val mSuccess = MutableLiveData<Boolean>()
    val mError = MutableLiveData<String>()

    fun registrateUser(email: String, password: String) {
        registrationUseCase.registrateUser(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    mSuccess.value = true
                },
                onError = {
                    mError.value = it.message
                }
            )
            .addToDisposable()
    }
}