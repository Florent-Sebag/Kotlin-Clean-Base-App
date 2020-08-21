package com.sebag.florent.presenter.di

import com.sebag.florent.presenter.view.HostActivity
import com.sebag.florent.presenter.view.fragments.auth.login.LoginFragment
import com.sebag.florent.presenter.view.fragments.home.HomeFragment
import com.sebag.florent.presenter.view.fragments.auth.registration.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun provideHostActivity() : HostActivity

    @ContributesAndroidInjector
    abstract fun provideLoginFragment() : LoginFragment

    @ContributesAndroidInjector
    abstract fun provideRegistrationFragment() : RegistrationFragment

    @ContributesAndroidInjector
    abstract fun provideHomeFragment() : HomeFragment
}