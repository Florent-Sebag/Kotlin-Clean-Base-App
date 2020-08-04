package com.sebag.florent.cleanbaseapp

import android.app.Application
import com.sebag.florent.cleanbaseapp.di.component.DaggerAppComponent

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}