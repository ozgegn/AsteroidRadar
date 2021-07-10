package com.ozgegn.asteroidradar.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ozgegn.asteroidradar.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object Network {

    private val retrofit =
        Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

    val asteroidsApi = retrofit.create(AsteroidsApi::class.java)

}