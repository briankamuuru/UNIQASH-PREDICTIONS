package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val UniQashColorScheme = lightColorScheme(
    primary = UniQashBluePrimary,
    onPrimary = UniQashCardBgWhite,
    primaryContainer = UniQashBlueLight,
    onPrimaryContainer = UniQashCardBgWhite,
    secondary = UniQashGold,
    onSecondary = UniQashTextPrimary,
    tertiary = UniQashBrightGreen,
    onTertiary = UniQashCardBgWhite,
    background = UniQashBgClean,
    onBackground = UniQashTextPrimary,
    surface = UniQashCardBgWhite,
    onSurface = UniQashTextPrimary,
    outline = UniQashBorderBlue,
    outlineVariant = UniQashBorderSubtle
)

@Composable
fun UniQashTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = UniQashBluePrimary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = UniQashColorScheme,
        typography = Typography,
        content = content
    )
}
