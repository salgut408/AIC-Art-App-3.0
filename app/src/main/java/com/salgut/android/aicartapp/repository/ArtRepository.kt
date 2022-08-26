package com.salgut.android.aicartapp.repository

import com.salgut.android.aicartapp.api.ArtAPI
import com.salgut.android.aicartapp.db.ArtworkDatabase
import com.salgut.android.aicartapp.models.ArtworkObject

class ArtRepository(
    val db: ArtworkDatabase,
    val apiService: ArtAPI,
    val cacheRep: CacheRepository
) {

    suspend fun getBreakingArt(fieldTerms: String, pageNumber: Int) =
        apiService.getBreakingArt(fieldTerms, pageNumber)
//        RetrofitInstance.api.getBreakingArt(fieldTerms, pageNumber)

 suspend fun insertAllCache(artwork: List<ArtworkObject>)=
    cacheRep.db.getCacheDao().insertAll(artwork)

    suspend fun searchArt(fieldTerms: String, searchQuery: String, pageNumber: Int) =
        apiService.searchForArt(fieldTerms, searchQuery, pageNumber)

    suspend fun upsert(artwork: ArtworkObject) = db.getArtworkDao().upsert(artwork)

    fun getSavedArt() = db.getArtworkDao().getAllArtworks()

    suspend fun deleteArtwork(artwork: ArtworkObject) = db.getArtworkDao().deleteArtwork(artwork)

}




















