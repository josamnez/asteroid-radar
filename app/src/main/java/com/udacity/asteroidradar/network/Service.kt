package com.udacity.asteroidradar.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class Service {

    /**
     * A retrofit service to fetch asteroids.
     */
    interface AsteroidService{
        @GET("neo/rest/v1/feed")
        fun getAsteroids(): Deferred<NetworkAsteroidContainer>
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

        // Configure retrofit to parse JSON and use coroutines
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val retrofitAsteroids = retrofit.create(AsteroidService::class.java)
    }
}