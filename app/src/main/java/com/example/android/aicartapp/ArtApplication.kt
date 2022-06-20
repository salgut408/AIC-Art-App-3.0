package com.example.android.aicartapp

import android.app.Application
import com.example.android.aicartapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArtApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ArtApplication)
            modules(appModule)
        }
    }
}