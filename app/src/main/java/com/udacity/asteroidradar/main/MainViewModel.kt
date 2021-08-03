package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    init {
        Log.i("MainViewModel", "MainViewModel Created!!" )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel Destroyed!!" )
    }
}


