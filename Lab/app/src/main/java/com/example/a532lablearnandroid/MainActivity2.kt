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

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _532LabLearnAndroidTheme {  }LabLearnAndoridTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                        .padding(32.dp)
                ) {
                    // hp
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .background(color = Color.White)
                    ) {
                        Text(
                            text = "hp",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .fillMaxWidth(fraction = 0.75f)
                                .background(color = Color.Red)
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