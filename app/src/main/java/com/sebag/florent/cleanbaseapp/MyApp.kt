package com.sebag.florent.cleanbaseapp

import android.app.Application
import com.sebag.florent.cleanbaseapp.di.component.DaggerAppComponent
import com.sebag.florent.domain.usecases.LoginUseCase
import com.sebag.florent.domain.usecases.UserManagerUseCase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    @Inject
    lateinit var userManagerUseCase: UserManagerUseCase

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
        checkIsConnectedUser()
    }

    private fun checkIsConnectedUser() {
        val isConnected = userManagerUseCase.isUserConnected()
        val editor = getSharedPreferences("prefs", MODE_PRIVATE).edit()
        editor.putBoolean("isConnected", isConnected)
        editor.apply()
    }
}