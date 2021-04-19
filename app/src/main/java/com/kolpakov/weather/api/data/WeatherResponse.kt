package com.kolpakov.weather.api.data

import com.kolpakov.weather.api.data.Icon
import com.kolpakov.weather.api.data.Temp
import com.kolpakov.weather.api.data.Wind

data class WeatherResponse(val weather: List<Icon>, val main: Temp, val wind: Wind)
