package com.example.a532lablearnandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorLocationViewModel(application: Application) : AndroidViewModel(application) {

    private val sensorTracker = SensorTracker(application)

    private val _sensorData = MutableStateFlow(floatArrayOf(0f, 0f, 0f))
    val sensorData: StateFlow<FloatArray> = _sensorData.asStateFlow()

    private val _locationData = MutableStateFlow<Pair<Double, Double>?>(null)
    val locationData: StateFlow<Pair<Double, Double>?> = _locationData.asStateFlow()

    private val _isTrackingLocation = MutableStateFlow(false)
    val isTrackingLocation: StateFlow<Boolean> = _isTrackingLocation.asStateFlow()

    init {
        // start reading sensor immediately as it doesn't require runtime permission in foreground
        sensorTracker.startAccelerometerProcess(_sensorData)
    }

    fun startLocationTracking() {
        sensorTracker.startLocationProcess(_locationData)
        _isTrackingLocation.value = true
    }

    fun stopLocationTracking() {
        sensorTracker.stopLocationProcess()
        _isTrackingLocation.value = false
    }
}
