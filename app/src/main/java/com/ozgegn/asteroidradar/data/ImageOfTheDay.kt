package com.ozgegn.asteroidradar.data

import com.ozgegn.asteroidradar.data.local.ImageOfTheDayEntity

data class ImageOfTheDay(
    val url: String,
    val mediaType: String,
    val title: String
)

fun ImageOfTheDay.toEntityModel(): ImageOfTheDayEntity {
    return ImageOfTheDayEntity(
        url = url,
        mediaType = mediaType,
        title = title
    )
}

