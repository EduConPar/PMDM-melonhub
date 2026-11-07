package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.melonhub.ui.theme.RetroBordeClaro
import com.example.melonhub.ui.theme.RetroBordeOscuro

fun Modifier.win98(
    hundido: Boolean = false,
    grosor: Dp = 1.dp,
    colorClaro: Color = RetroBordeClaro,
    colorOscuro: Color = RetroBordeOscuro,
    reservarEspacio: Boolean = true
): Modifier {
    val bisel = drawWithCache {
        val g = grosor.toPx().coerceAtMost(minOf(size.width, size.height) / 2f)
        val w = size.width
        val h = size.height

        val arriba = Path().apply {
            moveTo(0f, 0f); lineTo(w, 0f); lineTo(w - g, g); lineTo(g, g); close()
        }
        val izquierda = Path().apply {
            moveTo(0f, 0f); lineTo(g, g); lineTo(g, h - g); lineTo(0f, h); close()
        }
        val abajo = Path().apply {
            moveTo(0f, h); lineTo(g, h - g); lineTo(w - g, h - g); lineTo(w, h); close()
        }
        val derecha = Path().apply {
            moveTo(w, 0f); lineTo(w, h); lineTo(w - g, h - g); lineTo(w - g, g); close()
        }

        val arribaIzq = if (hundido) colorOscuro else colorClaro
        val abajoDer = if (hundido) colorClaro else colorOscuro

        onDrawWithContent {
            drawContent()
            drawPath(arriba, arribaIzq)
            drawPath(izquierda, arribaIzq)
            drawPath(abajo, abajoDer)
            drawPath(derecha, abajoDer)
        }
    }
    return this
        .then(bisel)
        .then(if (reservarEspacio) Modifier.padding(grosor) else Modifier)
}
