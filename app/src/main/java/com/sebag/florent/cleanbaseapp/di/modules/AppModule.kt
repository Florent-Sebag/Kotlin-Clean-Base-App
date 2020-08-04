package com.sebag.florent.cleanbaseapp.di.modules

import android.app.Application
import android.content.Context
import com.sebag.florent.cleanbaseapp.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(baseApp: MyApp) : Context = baseApp.applicationContext

}