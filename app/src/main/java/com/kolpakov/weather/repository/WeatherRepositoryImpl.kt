package com.kolpakov.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kolpakov.weather.api.WeatherNetworkSource
import com.kolpakov.weather.api.WeatherSource
import com.kolpakov.weather.database.RoomWeatherStorage
import com.kolpakov.weather.database.Weather
import com.kolpakov.weather.database.WeatherStorage
import com.kolpakov.weather.location.LocationProvider


class WeatherRepositoryImpl: WeatherRepository {

    private val weatherLiveData: MutableLiveData<Weather> = MutableLiveData<Weather>()
    private val weatherStorage: WeatherStorage = RoomWeatherStorage()
    private val weatherSource: WeatherSource = WeatherNetworkSource()
    private val locationProvider: LocationProvider = LocationProvider()

    override fun getWeatherLiveData(): LiveData<Weather> {
        getValueFromStorage()
        return weatherLiveData
    }


    override fun updateWeather() {
        locationProvider
            .getLocation()
            .flatMap { weatherSource.getWeather(it.latitude, it.longitude) }
            .subscribe({
                weatherStorage.putWeather(it).subscribe()
                weatherLiveData.postValue(it)
            },{
                Log.e("WeatherRepositoryImpl", "Update weather error" , it)
            })
    }

    private fun getValueFromStorage() {
        if(weatherLiveData.value == null) {
            weatherStorage.getWeather(1)
                .subscribe({
                    weatherLiveData.value = it
                },{
                    Log.e("WeatherRepositoryImpl", "Database is empty" , it)
                })
        }
    }

}