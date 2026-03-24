package com.example.a532lablearnandroid.ui.theme

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeType {
    DEFAULT,
    PEACOCK_BLUE,
    PEACOCK_GREEN,
    PEACOCK_PURPLE
}

object ThemeManager {
    private val _currentTheme = MutableStateFlow(ThemeType.DEFAULT)
    val currentTheme: StateFlow<ThemeType> = _currentTheme.asStateFlow()

    fun setTheme(theme: ThemeType, context: Context) {
        _currentTheme.value = theme
        context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("theme", theme.name)
            .apply()
    }

    fun loadTheme(context: Context) {
        val saved = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
            .getString("theme", ThemeType.DEFAULT.name) ?: ThemeType.DEFAULT.name
        _currentTheme.value = ThemeType.valueOf(saved)
    }
}
