package com.salgut.android.aicartapp.repository

import com.salgut.android.aicartapp.api.ArtAPI
import com.salgut.android.aicartapp.db.cacheDb.CacheDatabase
import com.salgut.android.aicartapp.models.ArtworkObject

class CacheRepository( val db: CacheDatabase,
                       val apiService: ArtAPI) {



 suspend fun insertAll(artworkObject: List<ArtworkObject>) =
    db.getCacheDao().insertAll(artworkObject)


}