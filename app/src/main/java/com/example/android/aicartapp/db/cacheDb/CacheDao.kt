package com.example.android.aicartapp.db.cacheDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.example.ArtResponse
import com.example.example.ArtworkObject
@Dao
interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artwork: List<ArtworkObject>)

    @Query("SELECT * FROM artworks")
    fun getAllArtworks(): LiveData<List<ArtworkObject>>

    @Query("DELETE FROM artworks")
    fun clear()

    @Delete
    suspend fun deleteArtwork(artwork: ArtworkObject)
}