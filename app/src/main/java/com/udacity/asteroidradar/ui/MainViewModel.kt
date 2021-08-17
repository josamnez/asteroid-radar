package com.udacity.asteroidradar.ui

import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid


class MainViewModel : ViewModel() {

    private val asteroids = mutableListOf<Asteroid>()

    init {
        for (i in 0 until 100) {
            val asteroid = Asteroid(
                1,
                "12",
                "Feb 31",
                2.3,
                3.4,
                43.3,
                212.2,
                true
            )
            asteroids += asteroid

        }
    }

}


