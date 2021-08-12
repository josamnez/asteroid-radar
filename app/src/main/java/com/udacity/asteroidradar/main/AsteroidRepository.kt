package com.udacity.asteroidradar.main

//
//class AsteroidRepository(private val database: AsteroidDatabase)  {
//
//
//    val allAsteroids: LiveData<List<Asteroid>> =
//        Transformations.map(database.asteroidDao.getAsteroids()) {
//            it.asDomainModel()
//        }
//
//
//    suspend fun refreshAsteroids() {
//        withContext(Dispatchers.IO) {
//            try {
//                val asteroids = AsteroidApiService.AsteroidApi.retrofitService.getAsteroids(API_KEY)
//                val networkResult = parseAsteroidsJsonResult(JSONObject(asteroids))
//                database.asteroidDao.insertAll(*networkResult.asDatabaseModel())
//                Log.d("Refresh Asteroids", "Success")
//            } catch (err: Exception) {
//                Log.e("Failed: AsteroidRepFile", err.message.toString())
//            }
//        }
//    }
//}