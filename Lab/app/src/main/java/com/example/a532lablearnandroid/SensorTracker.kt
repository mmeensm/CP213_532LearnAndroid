package com.example.a532lablearnandroid

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Looper
import android.util.Log
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
    private var locationCallback: LocationCallback? = null

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
        if (isLocationClientActive) {
            Log.d("SensorTracker", "Location client is already active.")
            return
        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(5000)
            .build()
        
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    Log.d("SensorTracker", "Location received: ${location.latitude}, ${location.longitude}")
                    locationDataState.value = Pair(location.latitude, location.longitude)
                }
            }
        }
        
        try {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!, Looper.getMainLooper())
            isLocationClientActive = true
            Log.d("SensorTracker", "Location updates requested.")
        } catch (e: SecurityException) {
            e.printStackTrace()
            Log.e("SensorTracker", "Location permission missing.")
        }
    }

    fun stopLocationProcess() {
        if (isLocationClientActive && locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback!!)
            isLocationClientActive = false
            locationCallback = null
            Log.d("SensorTracker", "Location updates stopped.")
        }
    }
}
