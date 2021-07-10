package com.ozgegn.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ozgegn.asteroidradar.data.AsteroidDetailState
import com.ozgegn.asteroidradar.data.local.AsteroidDatabase
import com.ozgegn.asteroidradar.data.local.toDisplayModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRepository(
    private val database: AsteroidDatabase
) {
    suspend fun getAsteroidDetail(id: Long): LiveData<AsteroidDetailState> {
        val data = MutableLiveData<AsteroidDetailState>()
        try {
            val asteroidDetail = withContext(Dispatchers.IO) {
                database.mainDao.getAsteroid(id)
            }
            data.value = AsteroidDetailState.Success(asteroidDetail.toDisplayModel())
        } catch (e: Exception) {
            data.value = AsteroidDetailState.Error(e.message)
        }
        return data
    }

}