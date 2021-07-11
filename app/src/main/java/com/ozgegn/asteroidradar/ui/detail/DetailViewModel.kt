package com.ozgegn.asteroidradar.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private val _infoClicked = MutableLiveData(false)
    val infoClicked: LiveData<Boolean>
        get() = _infoClicked

    fun onAstronomicalInfoClicked() {
        _infoClicked.value = true
    }

}