package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class Service {

    /**
     * A retrofit service to fetch asteroids.
     */
    interface AsteroidService{
        @GET("neo/rest/v1/feed")
        suspend fun getAsteroids(@Query("api_key") api_key: String): String

        @GET("planetary/apod")
        suspend fun getPictureOfTheDay(@Query("api_key") api_key: String): PictureOfDay
    }

    /**
     * Main entry point for network access. Call like `Service.Network.retrofitAsteroids.getAsteroids()`
     */

    object Network {
        /**
         * Build the Moshi object that Retrofit will be using.
         */
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        // Configure retrofit to parse JSON and use coroutines
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)

            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val retrofitAsteroids: AsteroidService by lazy { retrofit.create(AsteroidService::class.java) }

    }
}