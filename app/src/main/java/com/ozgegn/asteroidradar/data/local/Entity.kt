package com.ozgegn.asteroidradar.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.data.ImageOfTheDay

@Entity(tableName = "asteroid")
data class AsteroidEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val absolute_magnitude: Double,
    val estimated_diameter_max: Double,
    val is_potentially_hazardous_asteroid: Boolean,
    val kilometers_per_second: Double,
    val astronomical: Double,
    val closeApproachDate: String
)

@Entity(tableName = "imageOfTheDay")
data class ImageOfTheDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
    val mediaType: String,
    val title: String
)

fun List<AsteroidEntity>.toDisplayModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codeName = it.name,
            absoluteMagnitude = it.absolute_magnitude,
            estimatedDiameterMax = it.estimated_diameter_max,
            isPotentiallyHazardousAsteroid = it.is_potentially_hazardous_asteroid,
            distanceFromEarth = it.kilometers_per_second,
            relativeVelocity = it.astronomical,
            closeApproachDate = it.closeApproachDate
        )
    }
}

fun AsteroidEntity.toDisplayModel(): Asteroid {
    return Asteroid(
        id = id,
        codeName = name,
        absoluteMagnitude = absolute_magnitude,
        estimatedDiameterMax = estimated_diameter_max,
        isPotentiallyHazardousAsteroid = is_potentially_hazardous_asteroid,
        distanceFromEarth = kilometers_per_second,
        relativeVelocity = astronomical,
        closeApproachDate = closeApproachDate
    )
}

fun ImageOfTheDayEntity.toDisplayModel(): ImageOfTheDay {
    return ImageOfTheDay(
        url, mediaType, title
    )
}