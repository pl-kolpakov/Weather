package com.kolpakov.weather.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.kolpakov.weather.WeatherApp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LocationProvider {
    @SuppressLint("MissingPermission")
    fun getLocation() : Single<Location> {
        return Single.create<Location>{ singleEmitter ->
            LocationServices
                    .getFusedLocationProviderClient(WeatherApp.instanse.applicationContext)
                    .lastLocation
                    .addOnSuccessListener { singleEmitter.onSuccess(it) }
                    .addOnFailureListener { singleEmitter.onError(it) }
        }.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }
}