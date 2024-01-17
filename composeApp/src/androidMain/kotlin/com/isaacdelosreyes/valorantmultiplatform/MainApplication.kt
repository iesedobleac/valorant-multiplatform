package com.isaacdelosreyes.valorantmultiplatform

import android.app.Application
import di.RemoteModule
import di.RepositoryModule
import di.UseCaseModule
import di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                RemoteModule,
                RepositoryModule,
                UseCaseModule,
                ViewModelModule
            )
        }
    }
}