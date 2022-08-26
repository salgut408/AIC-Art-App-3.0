package com.salgut.android.aicartapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.salgut.android.aicartapp.models.ArtworkObject

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

















