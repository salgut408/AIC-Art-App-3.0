package com.example.android.aicartapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.example.ArtworkObject

@Database(
    entities = [ArtworkObject::class],
    version =1
)
//can move to singleton ...
abstract class ArtworkDatabase : RoomDatabase() {
    abstract fun getArtworkDao(): ArtworkDao
    companion object {
        @Volatile
        private var instance: ArtworkDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArtworkDatabase::class.java,
                "artwork_db.db"
            ).fallbackToDestructiveMigration()


                .build()
    }
}



























