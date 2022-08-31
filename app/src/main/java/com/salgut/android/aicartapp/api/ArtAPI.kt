package com.salgut.android.aicartapp.api

import com.salgut.android.aicartapp.models.ArtResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtAPI {

    @GET("artworks/search")
    suspend fun getAllArt(
        @Query("fields")
        fieldTerms: String,
        @Query("page")
        pageNumber: Int = 1

    ): Response<ArtResponse>

    @GET("artworks/search")
    suspend fun searchForArt(
        @Query("fields")
        fieldTerms: String,
        @Query("q")
        searchTerm: String,
        @Query("page")
        pageNumber: Int = 1

    ): Response<ArtResponse>

    @GET("artworks/search")
    suspend fun searchForArtStyleType(
        @Query("fields")
        fieldTerms: String,
        @Query("q")
        searchTerm: String,
        @Query("page")
        pageNumber: Int = 1

    ): Response<ArtResponse>
}