package com.kolpakov.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1)
abstract class RoomWeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}