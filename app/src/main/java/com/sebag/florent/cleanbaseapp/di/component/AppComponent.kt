package com.sebag.florent.cleanbaseapp.di.component

import com.sebag.florent.cleanbaseapp.MyApp
import com.sebag.florent.cleanbaseapp.di.modules.AppModule
import com.sebag.florent.data.di.*
import com.sebag.florent.domain.di.UseCaseModule
import com.sebag.florent.domain.models.User
import com.sebag.florent.presenter.di.BuilderModule
import com.sebag.florent.presenter.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        OkHttpModule::class,
        RetrofitModule::class,
        GsonModule::class,
        BuilderModule::class,
        ViewModelModule::class,
        RepositoryImplModule::class,
        UseCaseModule::class,
        ServiceModule::class,
        FirebaseModule::class,
        UserModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MyApp): Builder
        fun build(): AppComponent
    }

    fun inject(app: MyApp)
}