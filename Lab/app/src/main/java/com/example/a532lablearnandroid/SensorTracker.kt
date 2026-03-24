package com.example.a532lablearnandroid

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow

class SensorTracker(private val context: Context) {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private var isLocationClientActive = false

    // Accelerometer listener
    fun startAccelerometerProcess(sensorDataState: MutableStateFlow<FloatArray>) {
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                    sensorDataState.value = event.values.clone()
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { 
                // Not used
            }
        }
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    // Location listener
    fun startLocationProcess(locationDataState: MutableStateFlow<Pair<Double, Double>?>) {
        if (isLocationClientActive) return // Prevent multiple location requests

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(5000)
            .build()
        
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    locationDataState.value = Pair(location.latitude, location.longitude)
                }
            }
        }
        
        try {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            isLocationClientActive = true
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}
