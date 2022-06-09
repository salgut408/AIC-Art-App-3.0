package com.example.android.aicartapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.example.ArtworkObject

@Dao
interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(artwork: ArtworkObject): Long

    @Query("SELECT * FROM artworks")
    fun getAllArtworks(): LiveData<List<ArtworkObject>>

    @Query("DELETE FROM artworks")
    fun clear()

    @Delete
    suspend fun deleteArtwork(artwork: ArtworkObject)


}

















