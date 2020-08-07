package com.sebag.florent.cleanbaseapp.di.modules

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.sebag.florent.cleanbaseapp.MyApp
import com.sebag.florent.presenter.di.utils.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(baseApp: MyApp) : Context = baseApp.applicationContext

    @Provides
    @Singleton
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory
}