package com.example.android.aicartapp.api

import com.example.android.aicartapp.util.Constants.Companion.BASE_URL
import com.example.android.aicartapp.util.Constants.Companion.FIELD_TERMS
import com.example.example.ArtResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//
//class ArtApiService {
//    interface ArtService {
//
//        @GET("artworks/search")
//        suspend fun getBreakingArt(
//            @Query("fields")
//            fieldTerms: String = FIELD_TERMS,
//            @Query("page")
//            pageNumber: Int = 1
////            @Query("limit")
////            limitNo: Int = 50
//        ): Response<ArtResponse>
//
//        @GET("artworks/search")
//        suspend fun searchForArt(
//            @Query("fields")
//            fieldTerms: String = FIELD_TERMS,
//            @Query("q")
//            searchTerm: String,
//            @Query("page")
//            pageNumber: Int = 1
////            @Query("limit")
////            limitNo: Int = 50
//        ): Response<ArtResponse>
//
//    }
//    object ArtAPI {
//        private val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(
//                GsonConverterFactory.create()
//            ).build()
//        val retrofitService: ArtService by lazy {
//            retrofit.create(ArtService::class.java)
//        }
//    }
//}