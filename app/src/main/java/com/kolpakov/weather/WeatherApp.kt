package com.kolpakov.weather

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import com.kolpakov.weather.database.RoomWeatherDatabase


class WeatherApp: Application() {
    private lateinit var roomWeatherDatabase: RoomWeatherDatabase

    override fun onCreate() {
        super.onCreate()
        instanse = this
        roomWeatherDatabase = Room.databaseBuilder(
            applicationContext,
            RoomWeatherDatabase ::class.java,
            "roomWeatherDatabase"
        ).build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    fun getRoomWeatherDatabase(): RoomWeatherDatabase = roomWeatherDatabase

    companion object {
        lateinit var instanse: WeatherApp
    }
}