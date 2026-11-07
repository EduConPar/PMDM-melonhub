package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.win98
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto

@Composable
fun PeliculaDetalleScreen(
    modifier: Modifier = Modifier,
    pelicula: pelicula,
    onReseñaClick: (Long) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RetroPoster)
            .padding(8.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(RetroPanel)
                .win98(grosor = 2.dp),
            color = RetroPanel
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
                        .aspectRatio(1f)
                        .background(RetroPoster)
                        .win98(hundido = true, grosor = 2.dp),
                    painter = painterResource(R.drawable.image_mockup),
                    contentDescription = "Imagen Película"
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = pelicula.titulo,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = RetroTexto,
                    maxLines = 2,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.timer_mockup),
                        contentDescription = "Duración",
                        colorFilter = ColorFilter.tint(RetroAmarillo)
                    )

                    Text(
                        text = ": ${pelicula.duracion} min",
                        style = MaterialTheme.typography.headlineMedium,
                        color = RetroAmarillo
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.star_mockup),
                        contentDescription = "Puntuación Media",
                        colorFilter = ColorFilter.tint(RetroAmarillo)
                    )

                    Text(
                        text = ": ${String.format("%.1f", pelicula.puntuacionMedia)}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = RetroAmarillo
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(RetroNegro)
                .win98(hundido = true, grosor = 2.dp),
            color = RetroNegro
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "RESEÑAS DE LA PELÍCULA (${pelicula.numeroReseñas}):",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroAmarillo
                )
                Spacer(modifier = Modifier.height(8.dp))
                ReseñasPeliculasList(
                    modifier = Modifier.fillMaxWidth(),
                    reseñas = pelicula.reseñas,
                    onReseñaClick = onReseñaClick
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
        modifier = modifier
            .background(RetroPanel)
            .win98(grosor = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = RetroPanel,
            contentColor = RetroTexto
        ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .aspectRatio(1f)
                    .win98(hundido = true, grosor = 2.dp),
                painter = painterResource(R.drawable.capi),
                contentDescription = "Imagen Usuario"
            )
            Column {
                Text(
                    text = reseña.usuario,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroTexto
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
                            contentDescription = "Puntuación",
                            colorFilter = ColorFilter.tint(RetroAmarillo)
                        )
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "(${reseña.puntuacion})",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = RetroAmarillo
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "\"" + reseña.descripcion+ "\"",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = RetroAmarillo
                )
            }
        }
    }
}


@Composable
fun ReseñasPeliculasList (
    reseñas: List<reseña>,
    modifier: Modifier = Modifier,
    onReseñaClick: (Long) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reseñas) { reseñaItem ->
            ReseñasPeliculasCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onReseñaClick(reseñaItem.id) },
                reseña = reseñaItem,
            )
        }
    }
}

@Preview
@Composable
fun PeliculaDetalleScreenPreview() {
    val r1 = reseña(id = 1, puntuacion = 5, descripcion = "Gran película, efectos espectaculares.", usuario = "Edu")
    val p = pelicula(1, "Peli 1", 148, listOf(r1))
    MelonhubTheme {
        PeliculaDetalleScreen(
            modifier = Modifier,
            pelicula = p
        )
    }
}
