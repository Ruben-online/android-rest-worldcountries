package com.example.worldcountries.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,

    primaryContainer = LightBlue,
    onPrimaryContainer = DarkBlue,

    secondary = TextSecondary,
    onSecondary = White,

    background = Background,
    onBackground = TextPrimary,

    surface = White,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,

    outline = BorderColor,
    outlineVariant = BorderColor,

    error = ErrorRed,
    onError = White
)

@Composable
fun WorldCountriesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}