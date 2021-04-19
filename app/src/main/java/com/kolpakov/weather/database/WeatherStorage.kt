package com.kolpakov.weather.database

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface WeatherStorage {
    fun getWeather(id: Int) : Single<Weather>
    fun putWeather(weather: Weather) : Completable
}