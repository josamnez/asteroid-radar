package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApiService
import com.udacity.asteroidradar.database.getInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application){

    private val database = getInstance(application)
    private val asteroidRepository = AsteroidRepository(database)

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()

    // The external immutable LiveData for the request status String
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay


    init {
        viewModelScope.launch {
            updatePictureOfDay()
            asteroidRepository.refreshAsteroids()
        }
    }

    val asteroid = asteroidRepository.allAsteroids


    private suspend fun updatePictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                _pictureOfDay.postValue(
                    AsteroidApiService.AsteroidApi.retrofitService.getPictureOfDay(API_KEY)
                )
            } catch (err: Exception) {
                Log.e("refreshPictureOfDay", err.printStackTrace().toString())
            }
        }

    }

    class MainViewModelFactory(private val repository: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}


