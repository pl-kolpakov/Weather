package com.kolpakov.weather.api

import com.kolpakov.weather.database.Weather
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class WeatherNetworkSource: WeatherSource {

    var weatherApi: WeatherApi
    init {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    override fun getWeather(lat: Double, lon: Double): Single<Weather> {
       return weatherApi
           .getUser(lat, lon, UNITS, KEY)
           .map { Weather(1, it.main.temp, ICON_URL.replace("icon", it.weather[0].icon), it.wind.speed, it.wind.deg) }
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        const val URL: String = "https://api.openweathermap.org/data/2.5/"
        const val ICON_URL: String = "https://openweathermap.org/img/wn/icon@2x.png"
        const val KEY: String = "a8a47ee09b814a106be3bb5d676d0d43"
        const val UNITS: String = "metric"

    }
}
