package com.ozgegn.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.asteroidradar.data.local.getDatabase
import com.ozgegn.asteroidradar.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application
) : ViewModel() {

    private val database = getDatabase(application.applicationContext)
    private val mainRepository = MainRepository(database)

    fun getData(apiKey: String, startDay: String, endDay: String) {
        viewModelScope.launch {
            mainRepository.getFeed(apiKey, startDay, endDay)
            mainRepository.getImageOfTheDay(apiKey)
        }
    }

    val asteroids = mainRepository.asteroids
    val imageOfTheDay = mainRepository.imageOfTheDay

}