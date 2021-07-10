package com.ozgegn.asteroidradar.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.data.AsteroidDetailState
import com.ozgegn.asteroidradar.data.local.getDatabase
import com.ozgegn.asteroidradar.data.local.toDisplayModel
import com.ozgegn.asteroidradar.repository.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val application: Application
) : ViewModel() {

    private val database = getDatabase(application.applicationContext)
    private val detailRepository = DetailRepository(database)

    private val _asteroid = MutableLiveData<Asteroid>()
    val asteroid: LiveData<Asteroid>
        get() = _asteroid

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _infoClicked = MutableLiveData(false)
    val infoClicked: LiveData<Boolean>
        get() = _infoClicked

    fun getAsteroid(id: Long) {
        viewModelScope.launch {
            val asteroidStateValue = detailRepository.getAsteroidDetail(id)
            if (asteroidStateValue.value is AsteroidDetailState.Success) {
                _asteroid.value = (asteroidStateValue.value as AsteroidDetailState.Success).asteroid
            } else {
                _error.value = (asteroidStateValue.value as AsteroidDetailState.Error).error
                    ?: "Something happened"
            }
        }
    }

    fun onAstronomicalInfoClicked() {
        _infoClicked.value = true
    }

}