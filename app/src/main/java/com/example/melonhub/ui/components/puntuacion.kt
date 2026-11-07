package com.example.melonhub.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.melonhub.ui.theme.RetroAmarillo

@Composable
fun puntuacionEstrellas(
    puntuacion: Int,
    modifier: Modifier = Modifier,
    color: Color = RetroAmarillo,
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    val p = puntuacion.coerceIn(0, 5)
    Text(
        modifier = modifier,
        text = "★".repeat(p) + "☆".repeat(5 - p),
        style = style,
        fontWeight = FontWeight.Bold,
        color = color
    )
}
