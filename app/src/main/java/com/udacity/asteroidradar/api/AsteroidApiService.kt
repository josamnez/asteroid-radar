package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface AsteroidApiService{

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("api_key") api_key: String
    ): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") api_key: String
    ): PictureOfDay


    object AsteroidApi {
        val retrofitService: AsteroidApiService by lazy {
            retrofit.create(AsteroidApiService::class.java)
        }
    }
}

