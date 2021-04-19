package com.kolpakov.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kolpakov.weather.R
import com.kolpakov.weather.ui.viewmodel.WeatherViewModel

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.weather.observe(this, { weather ->
            findViewById<TextView>(R.id.weatherText).text = getString(R.string.temperature, weather.temp.toString())
            findViewById<TextView>(R.id.windSpeedText).text = getString(R.string.wind_speed, weather.speed.toString())
            Glide.with(this).load(weather.icon).centerInside().into(findViewById(R.id.weatherImage))
        })
        val hasLocationPermission =
            ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        if (hasLocationPermission) {
            viewModel.update()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_PERMISSION
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_weather, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.update()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.update()
                }
                return
            }
        }
    }

    
    companion object{
        const val REQUEST_PERMISSION = 1
    }
}