package com.example.android.aicartapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.aicartapp.api.ArtAPI
import com.example.android.aicartapp.db.cacheDb.CacheDatabase
import com.example.example.ArtworkObject

class CacheRepository( val db: CacheDatabase,
                       val apiService: ArtAPI) {



 suspend fun insertAll(artworkObject: List<ArtworkObject>) =
    db.getCacheDao().insertAll(artworkObject)


}