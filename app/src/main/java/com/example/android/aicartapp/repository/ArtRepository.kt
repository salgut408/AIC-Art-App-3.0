package com.example.android.aicartapp.repository

import com.example.android.aicartapp.api.ArtAPI
import com.example.android.aicartapp.db.ArtworkDatabase
import com.example.android.aicartapp.util.Constants.Companion.FIELD_TERMS
import com.example.example.ArtworkObject

class ArtRepository(
    val db: ArtworkDatabase,
    val apiService: ArtAPI
) {

    suspend fun getBreakingArt(fieldTerms: String, pageNumber: Int) =
        apiService.getBreakingArt(fieldTerms, pageNumber)
//        RetrofitInstance.api.getBreakingArt(fieldTerms, pageNumber)



    suspend fun searchArt(fieldTerms: String, searchQuery: String, pageNumber: Int) =
        apiService.searchForArt(fieldTerms, searchQuery, pageNumber)

    suspend fun upsert(artwork: ArtworkObject) = db.getArtworkDao().upsert(artwork)

    fun getSavedArt() = db.getArtworkDao().getAllArtworks()

    suspend fun deleteArtwork(artwork: ArtworkObject) = db.getArtworkDao().deleteArtwork(artwork)

}




















