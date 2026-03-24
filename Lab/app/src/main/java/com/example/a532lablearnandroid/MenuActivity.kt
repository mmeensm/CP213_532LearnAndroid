package com.example.a532lablearnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a532lablearnandroid.ui.theme.*

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _532LabLearnAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuDashboard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MenuDashboard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val currentTheme by ThemeManager.currentTheme.collectAsState()

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), MaterialTheme.colorScheme.background)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        
        Text(
            text = "Welcome to Lab",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Peacock Edition",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Theme Picker 
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Select Theme", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 12.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ThemeDot(color = Color(0xFF6650a4), isSelected = currentTheme == ThemeType.DEFAULT) { ThemeManager.setTheme(ThemeType.DEFAULT, context) }
                    ThemeDot(color = PeacockBlueLight, isSelected = currentTheme == ThemeType.PEACOCK_BLUE) { ThemeManager.setTheme(ThemeType.PEACOCK_BLUE, context) }
                    ThemeDot(color = PeacockGreenLight, isSelected = currentTheme == ThemeType.PEACOCK_GREEN) { ThemeManager.setTheme(ThemeType.PEACOCK_GREEN, context) }
                    ThemeDot(color = PeacockPurpleLight, isSelected = currentTheme == ThemeType.PEACOCK_PURPLE) { ThemeManager.setTheme(ThemeType.PEACOCK_PURPLE, context) }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Menu Buttons
        MenuActionCard(title = "RPG Card App", subtitle = "Task 0 / Basics", icon = "🎮") {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
        MenuActionCard(title = "List & Lifecycle", subtitle = "Lab Navigation", icon = "📋") {
            context.startActivity(Intent(context, ListActivity::class.java))
        }
        MenuActionCard(title = "Gallery Access", subtitle = "Task 1: Content Picker", icon = "🖼️") {
            context.startActivity(Intent(context, GalleryActivity::class.java))
        }
        MenuActionCard(title = "Sensor & GPS", subtitle = "Task 2 & 3: MVVM", icon = "📡") {
            context.startActivity(Intent(context, SensorLocationActivity::class.java))
        }
    }
}

@Composable
fun ThemeDot(color: Color, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuActionCard(title: String, subtitle: String, icon: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 28.sp, modifier = Modifier.padding(end = 16.dp))
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(text = subtitle, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}