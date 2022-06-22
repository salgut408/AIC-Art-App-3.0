package com.example.android.aicartapp.db.cacheDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.example.ArtworkObject
@Database(
    entities = [ArtworkObject::class],
    version =1, exportSchema = false
)

abstract class CacheDatabase : RoomDatabase() {
    abstract fun getCacheDao(): CacheDao
    companion object {
        @Volatile
        private var instance: CacheDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CacheDatabase::class.java,
                "cache_db.db"
            )   .fallbackToDestructiveMigration()
                .build()
    }

}