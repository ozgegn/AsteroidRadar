package com.ozgegn.asteroidradar.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidsApi {

    @GET("neo/rest/v1/feed")
    suspend fun getFeed(
        @Query("api_key") api_key: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String
    ): String

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(
        @Query("api_key") api_key: String
    ): String

}