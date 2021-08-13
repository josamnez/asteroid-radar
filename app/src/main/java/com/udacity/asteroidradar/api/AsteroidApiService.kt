package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.network.NetworkAsteroidContainer
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * A retrofit service to fetch a Asteroid list.
 */
interface AsteroidApiService{

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsAsync(): Deferred<NetworkAsteroidContainer>

//    @GET("neo/rest/v1/feed")
//    suspend fun getAsteroids(
//        @Query("api_key") api_key: String
//    ): String

//    @GET("planetary/apod")
//    suspend fun getPictureOfDay(
//        @Query("api_key") api_key: String
//    ): PictureOfDay


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

/**
 * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
 */
object AsteroidApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}

}



