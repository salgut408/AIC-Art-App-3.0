package com.salgut.android.aicartapp

import android.app.Application
import com.salgut.android.aicartapp.di.appModule
import com.salgut.android.aicartapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArtApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ArtApplication)
            modules(appModule,networkModule)
        }
    }
}