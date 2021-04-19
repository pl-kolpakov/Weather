package com.kolpakov.weather.api

import com.kolpakov.weather.database.Weather
import io.reactivex.rxjava3.core.Single

interface WeatherSource {
    fun getWeather(lat: Double, lon: Double) : Single<Weather>
}