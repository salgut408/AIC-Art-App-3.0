package com.example.android.aicartapp.di

import android.app.Application
import com.example.android.aicartapp.db.ArtworkDatabase
import com.example.android.aicartapp.repository.ArtRepository
import com.example.android.aicartapp.ui.MainArtViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainArtViewModel(androidApplication(), get()) }
    single { ArtRepository(get(), get()) }
    single { ArtworkDatabase(androidContext()) }
}

