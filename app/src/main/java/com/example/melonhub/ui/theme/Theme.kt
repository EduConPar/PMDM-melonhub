package com.example.melonhub.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val RetroColorScheme = darkColorScheme(
    primary = RetroAmarillo,
    onPrimary = RetroNegro,
    primaryContainer = RetroAmarillo,
    onPrimaryContainer = RetroNegro,
    secondary = RetroAmarillo,
    onSecondary = RetroNegro,
    secondaryContainer = RetroPanel,
    onSecondaryContainer = RetroTexto,
    tertiary = RetroAmarillo,
    onTertiary = RetroNegro,
    background = RetroPoster,
    onBackground = RetroTexto,
    surface = RetroNegro,
    onSurface = RetroTexto,
    surfaceVariant = RetroPanel,
    onSurfaceVariant = RetroTextoTenue,
    surfaceContainerLowest = RetroNegro,
    surfaceContainerLow = RetroPanel,
    surfaceContainer = RetroPanel,
    surfaceContainerHigh = RetroPanel,
    surfaceContainerHighest = RetroPanel,
    outline = RetroBordeClaro,
    outlineVariant = RetroBordeOscuro
)

private val RetroShapes = Shapes(
    extraSmall = RoundedCornerShape(0.dp),
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(0.dp),
    extraLarge = RoundedCornerShape(0.dp)
)

@Composable
fun MelonhubTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RetroColorScheme,
        typography = TipografiaRetro,
        shapes = RetroShapes,
        content = content
    )
}
