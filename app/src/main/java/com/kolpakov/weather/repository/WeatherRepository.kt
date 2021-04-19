package com.kolpakov.weather.repository

import androidx.lifecycle.LiveData
import com.kolpakov.weather.database.Weather

interface WeatherRepository {
    fun getWeatherLiveData() : LiveData<Weather>
    fun updateWeather()
}