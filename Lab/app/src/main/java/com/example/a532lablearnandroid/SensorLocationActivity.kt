package com.example.a532lablearnandroid

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.a532lablearnandroid.ui.theme._532LabLearnAndroidTheme

class SensorLocationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _532LabLearnAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val sensorLocationViewModel: SensorLocationViewModel = viewModel()
                    SensorLocationScreen(
                        viewModel = sensorLocationViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SensorLocationScreen(viewModel: SensorLocationViewModel, modifier: Modifier = Modifier) {
    val sensorData by viewModel.sensorData.collectAsState()
    val locationData by viewModel.locationData.collectAsState()
    val isTracking by viewModel.isTrackingLocation.collectAsState()

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            viewModel.startLocationTracking()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Accelerometer Data:")
        Text("X: ${sensorData[0]}")
        Text("Y: ${sensorData[1]}")
        Text("Z: ${sensorData[2]}")
        
        Spacer(modifier = Modifier.height(32.dp))

        Text("Location GPS (Lat, Lng):")
        if (locationData != null) {
            Text("Lat: ${locationData?.first}")
            Text("Lng: ${locationData?.second}")
        } else {
            Text("No Location Data")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Status: ${if (isTracking) "Tracking Update Active" else "Stopped"}")

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = {
                    val hasFineLocation = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                    val hasCoarseLocation = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasFineLocation || hasCoarseLocation) {
                        viewModel.startLocationTracking()
                    } else {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                },
                enabled = !isTracking
            ) {
                Text("Start GPS")
            }

            Button(
                onClick = {
                    viewModel.stopLocationTracking()
                },
                enabled = isTracking
            ) {
                Text("Stop GPS")
            }
        }
    }
}
