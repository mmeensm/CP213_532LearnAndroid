package com.example.a532lablearnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RPGCardView()
        }
    }

    @Composable
    fun RPGCardView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
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
                        .fillMaxWidth(fraction = 0.55f)
                        .background(color = Color.Red)
                        .padding(8.dp)
                )
            }
            // image
            Image(
                painter = painterResource(R.drawable.eevee),
                contentDescription = "My Image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 32.dp)
                    .clickable {
                        startActivity(Intent(this@MainActivity, ListActivity::class.java))
                    }
            )

            var str by remember { mutableStateOf(8) }
            var agi by remember { mutableStateOf(10) }
            var int by remember { mutableStateOf(15) }
            // status
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Button(onClick = {
                        str = str + 1
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                            contentDescription = "up",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(text = "Strength", fontSize = 16.sp)
                    Text(text = str.toString(), fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                        contentDescription = "down",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                str = str - 1
                            }
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Button(onClick = {
                        agi = agi + 1
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                            contentDescription = "up",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(text = "Agility", fontSize = 16.sp)
                    Text(text = agi.toString(), fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                        contentDescription = "down",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                agi = agi - 1

                            }
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Button(onClick = {
                        int = int + 1
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.outline_arrow_drop_up_24),
                            contentDescription = "up",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(text = "Intelligence", fontSize = 16.sp)
                    Text(text = int.toString(), fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.drawable.outline_arrow_drop_down_24),
                        contentDescription = "down",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                int = int - 1
                            }
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        @Preview
        @Composable
        fun previewScreen() {
            RPGCardView()
        }
    }
}