package com.sebag.florent.presenter.di

import com.sebag.florent.presenter.view.HostActivity
import com.sebag.florent.presenter.view.fragments.detail.AuthFragment
import com.sebag.florent.presenter.view.fragments.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun provideHostActivity() : HostActivity

    @ContributesAndroidInjector
    abstract fun provideHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun provideDetailFragment() : AuthFragment
}