package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel(repository: AsteroidRepository) : ViewModel() {
    init {
        Log.i("MainViewModel", "MainViewModel Created $repository!!" )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel Destroyed!!" )
    }
}


