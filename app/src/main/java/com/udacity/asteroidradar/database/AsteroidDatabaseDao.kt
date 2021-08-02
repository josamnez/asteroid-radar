package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDatabaseDao {

    @Insert
    suspend fun insert(asteroid: Asteroid)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param asteroid new value to write
     */
    @Update
    suspend fun update(asteroid: Asteroid)

}