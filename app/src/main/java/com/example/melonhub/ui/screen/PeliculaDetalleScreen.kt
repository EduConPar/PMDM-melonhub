package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña

@Composable
fun PeliculaDetalleScreen(
    modifier: Modifier = Modifier,
    pelicula: pelicula,
    onReseñaClick: (Long) -> Unit = {}
) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    painter = painterResource(R.drawable.image_mockup),
                    contentDescription = "Imagen Película"
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = pelicula.titulo,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 2,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.timer_mockup),
                        contentDescription = "Duración"
                    )

                    Text(text = ": ${pelicula.duracion} min", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.star_mockup),
                        contentDescription = "Puntuación Media"
                    )

                    Text(text = ": ${String.format("%.1f", pelicula.puntuacionMedia)}", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "RESEÑAS DE LA PELÍCULA (${pelicula.numeroReseñas}):",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReseñasPeliculasList(
                    modifier = Modifier.fillMaxWidth(),
                    reseñas = pelicula.reseñas,
                )
            }
        }
    }
}

@Composable
fun ReseñasPeliculasCard (
    reseña: reseña,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .aspectRatio(1f)
                    .border(
                        width = 2.dp,
                        color = Color(0xFFC0C0C0),
                        shape = RectangleShape
                    ),
                painter = painterResource(R.drawable.capi),
                contentDescription = "Imagen Usuario"
            )
            Column {
                Text(
                    text = reseña.usuario,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..reseña.puntuacion.coerceIn(1, 5)) {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(R.drawable.star_mockup),
                            contentDescription = "Puntuación"
                        )
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "(${reseña.puntuacion})",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "\"" + reseña.descripcion+ "\"",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                )
            }
        }
    }
}


@Composable
fun ReseñasPeliculasList (
    reseñas: List<reseña>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reseñas) {
            ReseñasPeliculasCard(
                modifier = Modifier.fillMaxWidth(),
                reseña = reseñas.random(),
            )
        }
    }
}

@Preview
@Composable
fun PeliculaDetalleScreenPreview() {
    val r1 = reseña(id = 1, puntuacion = 5, descripcion = "Gran película, efectos espectaculares.", usuario = "Edu")
    val p = pelicula(1, "Peli 1", 148, listOf(r1))
    PeliculaDetalleScreen(
        modifier = Modifier,
        pelicula = p
    )
}
