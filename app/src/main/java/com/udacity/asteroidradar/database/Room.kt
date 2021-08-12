package com.udacity.asteroidradar.database

//
//@Dao
//interface AsteroidDao {
//
//    @Query("SELECT * FROM asteroids_table")
//    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg asteroid: DatabaseAsteroid)
//
//}
//
//
//@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
//abstract class AsteroidDatabase : RoomDatabase() {
//
//    abstract val asteroidDao: AsteroidDao
//
//}

/**
 * Singleton pattern: instance of the database
 */
//private lateinit var INSTANCE: AsteroidDatabase
//
//fun getInstance(context: Context): AsteroidDatabase {
//    synchronized(AsteroidDatabase::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(
//                context.applicationContext,
//                AsteroidDatabase::class.java,
//                "asteroids_records"
//            ).build()
//        }
//    }
//    return INSTANCE
//
//}


