package com.example.a532lablearnandroid

import android.content.Intent
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

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
                                .background(Color.LightGray)
                                .padding(8.dp)
                        )
                    }

                    // image
                    Image(
                        painter =  painterResource(id = R.drawable.eevee),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                startActivity(Intent(this@MainActivity2, ListActivity::class.java))
                            }

                    )

                    var str by remember { mutableStateOf(8) }
                    var agi by remember { mutableStateOf(10) }
                    var int by remember { mutableStateOf(12) }


                    // status
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Column (
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Button(onClick = {
                                str = str + 1
                            }) {
                                Image(
                                    painter =  painterResource(id = R.drawable.outline_arrow_drop_up_24),
                                    contentDescription = "up",
                                    modifier = Modifier.size( 32.dp)
                                )
                            }
                            Text(text = "Strength", fontSize = 16.sp )
                            Text(text = str.toString() , fontSize = 16.sp)
                            Image(
                                painter =  painterResource(id = R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down",
                                modifier = Modifier
                                    .size( 32.dp)
                                    .clickable {
                                        str = str - 1
                                    }
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                        Column (
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Button(onClick = {
                                agi = agi + 1
                            }) {
                                Image(
                                    painter =  painterResource(id = R.drawable.outline_arrow_drop_up_24),
                                    contentDescription = "up",
                                    modifier = Modifier.size( 32.dp)
                                )
                            }
                            Text(text = "Agility", fontSize = 16.sp )
                            Text(text = agi.toString() , fontSize = 16.sp)
                            Image(
                                painter =  painterResource(id = R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down",
                                modifier = Modifier
                                    .size( 32.dp)
                                    .clickable {
                                        agi = agi - 1
                                    }
                            )
                        }

                        Column (
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Button(onClick = {
                                int = int + 1
                            }) {
                                Image(
                                    painter =  painterResource(id = R.drawable.outline_arrow_drop_up_24),
                                    contentDescription = "up",
                                    modifier = Modifier.size( 32.dp)
                                )
                            }
                            Text(text = "Intelligence", fontSize = 16.sp )
                            Text(text = int.toString() , fontSize = 16.sp)
                            Image(
                                painter =  painterResource(id = R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down",
                                modifier = Modifier
                                    .size( 32.dp)
                                    .clickable {
                                        int = int - 1
                                    }
                            )
                        }
                    }

                }
            }

        }
    }
}
