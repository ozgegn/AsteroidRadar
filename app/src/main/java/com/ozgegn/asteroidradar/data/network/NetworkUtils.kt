package com.ozgegn.asteroidradar.data.network

import android.util.Log
import com.ozgegn.asteroidradar.Constants
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.data.ImageOfTheDay
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun parseAsteroidsJsonResult(jsonResult: JSONObject): ArrayList<Asteroid> {
    val nearEarthObjectsJson = jsonResult.getJSONObject("near_earth_objects")

    val asteroidList = ArrayList<Asteroid>()
    val nextSevenDaysFormattedDates = getNextSevenDaysFormattedDates()

    for (formattedDate in nextSevenDaysFormattedDates) {
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(formattedDate)

        for (i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
            val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
                .getJSONObject("kilometers").getDouble("estimated_diameter_max")

            val closeApproachData = asteroidJson
                .getJSONArray("close_approach_data").getJSONObject(0)
            val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
                .getDouble("kilometers_per_second")
            val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
                .getDouble("astronomical")
            val isPotentiallyHazardous = asteroidJson
                .getBoolean("is_potentially_hazardous_asteroid")
            val closeApproachDate = closeApproachData.getString("close_approach_date")

            val asteroid = Asteroid(
                id,
                codename,
                absoluteMagnitude,
                estimatedDiameter,
                isPotentiallyHazardous,
                relativeVelocity,
                distanceFromEarth,
                closeApproachDate
            )
            asteroidList.add(asteroid)
        }
    }
    return asteroidList
}

private fun getNextSevenDaysFormattedDates(): ArrayList<String> {
    val formattedDateList = ArrayList<String>()

    val calender = Calendar.getInstance()
    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
        val currentTime = calender.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        formattedDateList.add(dateFormat.format(currentTime))
        calender.add(Calendar.DAY_OF_YEAR, 1)
    }
    return formattedDateList
}

fun parseImageOfTheDayJsonResult(jsonResult: JSONObject): ImageOfTheDay {
    val url = jsonResult.getString("url")
    val mediaType = jsonResult.getString("media_type")
    val title = jsonResult.getString("title")
    return ImageOfTheDay(url, mediaType, title)
}