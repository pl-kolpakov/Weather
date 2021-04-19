package com.kolpakov.weather.api

import com.kolpakov.weather.api.data.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    fun getUser(@Query("lat") lat: Double,
                @Query("lon") lon: Double,
                @Query("units") units: String,
                @Query("appid") appid: String
    ): Single<WeatherResponse>
}