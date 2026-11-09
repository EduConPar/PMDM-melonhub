package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import com.example.melonhub.ui.theme.RetroAmarillo

@Composable
fun puntuacionEstrellas(
    puntuacion: Double,
    modifier: Modifier = Modifier,
    color: Color = RetroAmarillo,
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    val mediaUnidad = ((puntuacion.coerceIn(0.0, 10.0) + 1) / 2).toInt()
    val estrellasEnteras = mediaUnidad / 2
    val mediaEstrella = mediaUnidad % 2 == 1
    val anchoEstrella = 10.dp
    val altoEstrella = 20.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        for (indice in 0 until 5) {
            val completa = indice < estrellasEnteras
            val media = !completa && mediaEstrella && indice == estrellasEnteras

            Box(
                modifier = Modifier
                    .width(anchoEstrella)
                    .height(altoEstrella),
                contentAlignment = Alignment.Center
            ) {
                when {
                    completa -> Text(
                        "★",
                        style = style,
                        fontWeight = FontWeight.Bold,
                        color = color,
                    )
                    media -> {
                        Box(
                            modifier = Modifier
                                .width(anchoEstrella / 2)
                                .height(altoEstrella)
                                .clipToBounds(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                modifier = Modifier.width(anchoEstrella),
                                text = "★",
                                style = style,
                                fontWeight = FontWeight.Bold,
                                color = color

                            )
                        }
                    }
                    else -> Text(
                        "☆",
                        style = style,
                        fontWeight = FontWeight.Bold,
                        color = color.copy(alpha = 0.45f)

                    )
                }
            }
        }
    }
}
