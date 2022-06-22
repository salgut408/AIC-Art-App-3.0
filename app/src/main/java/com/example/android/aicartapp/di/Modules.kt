package com.example.android.aicartapp.di

import android.app.Application
import androidx.room.Room
import com.example.android.aicartapp.db.ArtworkDatabase
import com.example.android.aicartapp.db.cacheDb.CacheDatabase
import com.example.android.aicartapp.repository.ArtRepository
import com.example.android.aicartapp.repository.CacheRepository
import com.example.android.aicartapp.ui.MainArtViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainArtViewModel(androidApplication(), get()) }
    single { ArtRepository(get(), get(),get()) }
    single { ArtworkDatabase(androidContext()) }
    single {get<ArtworkDatabase>().getArtworkDao()}
    single { CacheDatabase(androidContext()) }
    single { CacheRepository(get(), get()) }


//    single { Room.databaseBuilder(androidContext(), ArtworkDatabase::class.java, "artDB" )
//        .fallbackToDestructiveMigration()
//        .build()}
}





