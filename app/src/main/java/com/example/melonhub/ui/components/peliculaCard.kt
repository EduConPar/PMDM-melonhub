package com.example.melonhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import kotlin.math.roundToInt

@Composable
fun peliculaCard(
    pelicula: pelicula,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = modifier,
        onClick = { onClick(pelicula.id) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(R.drawable.image_mockup),
                contentDescription = "Imagen Película"
            )
            Column {
                Text(text = pelicula.titulo, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val puntuacionInt = pelicula.puntuacionMedia.roundToInt()
                    for (i in 1..puntuacionInt.coerceIn(1, 5)) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.star_mockup),
                            contentDescription = "Puntuación"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${String.format("%.1f", pelicula.puntuacionMedia)} (${pelicula.numeroReseñas})",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
