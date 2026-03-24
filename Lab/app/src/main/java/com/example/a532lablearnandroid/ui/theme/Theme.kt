package com.example.a532lablearnandroid.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = PeacockBackgroundDark
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = PeacockBackgroundLight
)

private val PeacockBlueLightScheme = lightColorScheme(primary = PeacockBlueLight, secondary = PeacockBlueLight, background = PeacockBackgroundLight)
private val PeacockBlueDarkScheme = darkColorScheme(primary = PeacockBlueDark, secondary = PeacockBlueDark, background = PeacockBackgroundDark)

private val PeacockGreenLightScheme = lightColorScheme(primary = PeacockGreenLight, secondary = PeacockGreenLight, background = PeacockBackgroundLight)
private val PeacockGreenDarkScheme = darkColorScheme(primary = PeacockGreenDark, secondary = PeacockGreenDark, background = PeacockBackgroundDark)

private val PeacockPurpleLightScheme = lightColorScheme(primary = PeacockPurpleLight, secondary = PeacockPurpleLight, background = PeacockBackgroundLight)
private val PeacockPurpleDarkScheme = darkColorScheme(primary = PeacockPurpleDark, secondary = PeacockPurpleDark, background = PeacockBackgroundDark)

@Composable
fun _532LabLearnAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Force false so our colorful themes take effect
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        ThemeManager.loadTheme(context)
    }

    val themeType by ThemeManager.currentTheme.collectAsState()

    val colorScheme = when (themeType) {
        ThemeType.PEACOCK_BLUE -> if (darkTheme) PeacockBlueDarkScheme else PeacockBlueLightScheme
        ThemeType.PEACOCK_GREEN -> if (darkTheme) PeacockGreenDarkScheme else PeacockGreenLightScheme
        ThemeType.PEACOCK_PURPLE -> if (darkTheme) PeacockPurpleDarkScheme else PeacockPurpleLightScheme
        ThemeType.DEFAULT -> {
            if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            } else {
                if (darkTheme) DarkColorScheme else LightColorScheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}