package com.udacity.asteroidradar.ui

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.network.Service
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)

    /**
     * This is private to avoid exposing a way to set this value to observers.
     */
    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    private val _goToAsteroidDetail = MutableLiveData<Asteroid>()

    /**
     * Views should use this to get access to the data.
     */
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    val goToAsteroidDetail: LiveData<Asteroid>
        get() = _goToAsteroidDetail

        private var _filterAsteroidDate = MutableLiveData(FilterAsteroidDate.ALL)



    val asteroidListing = Transformations.switchMap(_filterAsteroidDate) {
        when (it!!) {
            FilterAsteroidDate.NEXT_WEEK -> asteroidsRepository.weekAsteroids
            FilterAsteroidDate.TODAY -> asteroidsRepository.todayAsteroids
            else -> asteroidsRepository.allAsteroids
        }
    }

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            asteroidsRepository.refreshAsteroids()
            refreshPictureOfDay()
        }
    }


    fun onClickedAsteroid(asteroid: Asteroid) {
        _goToAsteroidDetail.value = asteroid
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun onNavigatedAsteroid() {
        _goToAsteroidDetail.value = null
    }

    fun onDateChangeFilter(filter: FilterAsteroidDate) {
        _filterAsteroidDate.postValue(filter)
    }

    /**
     * Factory for constructing MainViewModel
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
    private fun refreshPictureOfDay() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                _pictureOfDay.postValue(
                    Service.Network.retrofitAsteroids.getPictureOfTheDay(API_KEY)
                )
            } catch (err: Exception) {
                Log.e("refreshPictureOfDay", err.printStackTrace().toString())
            }
        }
    }

}

enum class FilterAsteroidDate {
    TODAY, NEXT_WEEK, ALL
}


