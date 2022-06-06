package com.example.android.aicartapp.api

import com.example.android.aicartapp.util.Constants
import com.example.android.aicartapp.util.Constants.Companion.FIELD_TERMS
import com.example.example.ArtResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtAPI {

    @GET("artworks/search")
    suspend fun getBreakingArt(
        @Query("fields")
        fieldTerms: String = FIELD_TERMS,
        @Query("page")
        pageNumber: Int = 1
//            @Query("limit")
//            limitNo: Int = 50
    ): Response<ArtResponse>

    @GET("artworks/search")
    suspend fun searchForArt(
        @Query("fields")
        fieldTerms: String = Constants.FIELD_TERMS,
        @Query("q")
        searchTerm: String,
        @Query("page")
        pageNumber: Int = 1
//            @Query("limit")
//            limitNo: Int = 50
    ): Response<ArtResponse>
}