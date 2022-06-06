package com.example.android.aicartapp.repository

import com.example.android.aicartapp.api.RetrofitInstance
import com.example.android.aicartapp.db.ArtworkDatabase
import com.example.android.aicartapp.util.Constants.Companion.FIELD_TERMS

class ArtRepository(
    val db: ArtworkDatabase
) {

    suspend fun getBreakingArt(fieldTerms: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingArt(fieldTerms, pageNumber)
}

    suspend fun searchNews(fieldTerms: String, searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForArt(fieldTerms, searchQuery, pageNumber)