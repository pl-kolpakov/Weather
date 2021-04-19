package com.kolpakov.weather.database

import com.kolpakov.weather.WeatherApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RoomWeatherStorage : WeatherStorage {

    override fun getWeather(id: Int): Single<Weather> {
        return Single
            .fromCallable { WeatherApp.instanse.getRoomWeatherDatabase().weatherDao().getWeather(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putWeather(weather: Weather): Completable {
        return Completable
            .fromCallable { WeatherApp.instanse.getRoomWeatherDatabase().weatherDao().insertWeather(weather) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}