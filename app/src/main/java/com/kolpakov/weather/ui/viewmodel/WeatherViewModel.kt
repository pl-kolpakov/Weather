package com.kolpakov.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kolpakov.weather.database.Weather
import com.kolpakov.weather.repository.WeatherRepository
import com.kolpakov.weather.repository.WeatherRepositoryImpl

class WeatherViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository =  WeatherRepositoryImpl()
    val weather : LiveData<Weather> by lazy {
        weatherRepository.getWeatherLiveData()
    }
    fun update() {
        weatherRepository.updateWeather()
    }
}