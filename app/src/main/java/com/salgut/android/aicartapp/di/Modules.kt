package com.salgut.android.aicartapp.di

import com.salgut.android.aicartapp.db.ArtworkDatabase
import com.salgut.android.aicartapp.db.cacheDb.CacheDatabase
import com.salgut.android.aicartapp.repository.ArtRepository
import com.salgut.android.aicartapp.repository.CacheRepository
import com.salgut.android.aicartapp.ui.MainArtViewModel
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





