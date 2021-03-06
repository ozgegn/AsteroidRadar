package com.ozgegn.asteroidradar.data

import android.os.Parcelable
import com.ozgegn.asteroidradar.data.local.AsteroidEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    val id: Long,
    val codeName: String,
    val absoluteMagnitude: Double,
    val estimatedDiameterMax: Double,
    val isPotentiallyHazardousAsteroid: Boolean,
    val distanceFromEarth: Double,
    val relativeVelocity: Double,
    val closeApproachDate: String
): Parcelable


fun List<Asteroid>.toEntityModel(): Array<AsteroidEntity> {
    return map {
        AsteroidEntity(
            id = it.id,
            name = it.codeName,
            absolute_magnitude = it.absoluteMagnitude,
            estimated_diameter_max = it.estimatedDiameterMax,
            is_potentially_hazardous_asteroid = it.isPotentiallyHazardousAsteroid,
            kilometers_per_second = it.distanceFromEarth,
            astronomical = it.relativeVelocity,
            closeApproachDate = it.closeApproachDate
        )
    }.toTypedArray()
}