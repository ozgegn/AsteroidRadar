package com.ozgegn.asteroidradar.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {

    @Query("SELECT * FROM asteroid")
    fun getFeed(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroid WHERE id=:id")
    fun getAsteroid(id: Long): AsteroidEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageOfTheDay(imageOfTheDayEntity: ImageOfTheDayEntity)

    @Query("SELECT * FROM imageOfTheDay ORDER BY id DESC LIMIT 1")
    fun getImageOfTheDay(): LiveData<ImageOfTheDayEntity?>

    @Query("DELETE FROM asteroid")
    fun clearAll()

}