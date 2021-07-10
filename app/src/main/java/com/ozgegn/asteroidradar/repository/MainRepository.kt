package com.ozgegn.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.data.ImageOfTheDay
import com.ozgegn.asteroidradar.data.local.AsteroidDatabase
import com.ozgegn.asteroidradar.data.local.toDisplayModel
import com.ozgegn.asteroidradar.data.network.Network
import com.ozgegn.asteroidradar.data.network.parseAsteroidsJsonResult
import com.ozgegn.asteroidradar.data.network.parseImageOfTheDayJsonResult
import com.ozgegn.asteroidradar.data.toEntityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainRepository(
    private val database: AsteroidDatabase
) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.mainDao.getFeed()) {
        it.toDisplayModel()
    }

    val imageOfTheDay: LiveData<ImageOfTheDay> =
        Transformations.map(database.mainDao.getImageOfTheDay()) {
            it?.toDisplayModel()
        }

    suspend fun getFeed(apiKey: String, startDate: String, endDate: String) {
        try {
            withContext(Dispatchers.IO) {
                val asteroidResultString =
                    Network.asteroidsApi.getFeed(apiKey, startDate, endDate)
                val asteroidResultJson = JSONObject(asteroidResultString)
                val asteroidList = parseAsteroidsJsonResult(asteroidResultJson)
                database.mainDao.insertAll(*asteroidList.toEntityModel())
            }
        } catch (e: Exception) {
            Log.d("Error", "${e.message}")
        }
    }

    suspend fun getImageOfTheDay(apiKey: String) {
        try {
            withContext(Dispatchers.IO) {
                val imageOfTheDayResult = Network.asteroidsApi.getImageOfTheDay(apiKey)
                val imageOfTheDayJson = JSONObject(imageOfTheDayResult)
                val imageOfTheDay = parseImageOfTheDayJsonResult(imageOfTheDayJson)
                database.mainDao.insertImageOfTheDay(imageOfTheDay.toEntityModel())
            }
        } catch (e: Exception) {
            Log.d("Error", "${e.message}")
        }
    }

    suspend fun clearData() {
        try {
            withContext(Dispatchers.IO) {
                database.mainDao.clearAll()
            }
        } catch (e: Exception) {
            Log.d("Error", "${e.message}")
        }
    }
}