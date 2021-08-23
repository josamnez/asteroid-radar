package com.udacity.asteroidradar.repository

import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.network.Service
import com.udacity.asteroidradar.network.Service.Network.retrofitAsteroids
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  Repository for fetching asteroids from the network and storing them on disk.
 */

class AsteroidsRepository(private val database: AsteroidsDatabase) {

    /**
     * Load asteroids that can be shown on the screen
     */

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    /**
     * Upload (refresh) asteroids from the offline cache
     */
    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO) {
            val asteroidsList = Service.Network.retrofitAsteroids.getAsteroids().await()
            database.asteroidDao.insertAll(*asteroidsList.asDatabaseModel())
        }
    }

}