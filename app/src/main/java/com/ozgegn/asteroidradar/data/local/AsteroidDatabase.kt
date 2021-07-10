package com.ozgegn.asteroidradar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AsteroidEntity::class, ImageOfTheDayEntity::class], version = 5, exportSchema = false
)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val mainDao: MainDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidEntity::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context, AsteroidDatabase::class.java, "asteroid_db").fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}