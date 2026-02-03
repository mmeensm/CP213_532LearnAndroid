package com.example.a532lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a532lablearnandroid.ui.theme._532LabLearnAndroidTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _532LabLearnAndroidTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                        .padding(32.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = "hp",
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .fillMaxWidth(0.32f)
                                .background(Color.Green)
                                .padding(8.dp)
                        )
                    }

                    // image
                    // status

                }
            }
        }
    }
}
