package com.kolpakov.weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(@PrimaryKey
              val id: Int = 1,
              val temp: Float,
              val icon: String,
              val speed: Float,
              val deg: Float)