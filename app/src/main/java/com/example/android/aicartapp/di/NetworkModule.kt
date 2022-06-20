package com.example.android.aicartapp.di

import com.example.android.aicartapp.api.ArtAPI
import com.example.android.aicartapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideLogging()}
    single { provideClient() }
    single { provideRetrofit(get()) }

}

 fun provideLogging(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}
 fun provideClient(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    return okHttpClient.build()
}

fun provideRetrofit(client: OkHttpClient): ArtAPI{
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    return retrofit.create(ArtAPI::class.java)
}


