package com.ozgegn.asteroidradar.data

sealed class AsteroidDetailState {
    data class Success(val asteroid: Asteroid) : AsteroidDetailState()
    data class Error(val error: String?) : AsteroidDetailState()
}