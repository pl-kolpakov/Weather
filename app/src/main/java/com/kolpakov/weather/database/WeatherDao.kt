package com.kolpakov.weather.database

import androidx.room.*

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)

    @Query("SELECT * FROM weather WHERE id = :id")
    fun getWeather(id: Int) : Weather
}