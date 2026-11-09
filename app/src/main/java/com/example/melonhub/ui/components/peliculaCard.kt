package com.example.melonhub.ui.components

import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto
import kotlin.math.roundToInt

@Composable
fun peliculaCard(
    pelicula: pelicula,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier.win98(grosor = 1.dp),
        onClick = { onClick(pelicula.id) }
    ) {
        Column (
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row (
                modifier = Modifier
                    .background(RetroPoster)
                    .fillMaxWidth()
            ) {
                Text(
                    text = " ${pelicula.titulo}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroAmarillo,
                    fontSize = 20.sp
                )
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                painter = painterResource(R.drawable.image_mockup),
                contentDescription = "Pelicula",
                contentScale = ContentScale.Crop
            )

            Row (
                modifier = Modifier
                    .background(RetroPoster)
                    .fillMaxWidth()
            ) {
                puntuacionEstrellas(puntuacion = pelicula.puntuacionMedia * 2)
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "  ·  ${pelicula.puntuacionMedia / 2}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroAmarillo,
                    fontSize = 15.sp
                )
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun PeliculaCardPreview() {
    MelonhubTheme {
        peliculaCard(
            pelicula = pelicula(
                1L,
                "Matrix",
                136,
                listOf(
                    reseña(1L, 5.0, "Excelente película."),
                    reseña(2L, 5.0, "Muy buena.")
                )
            ),
            onClick = {}
        )
    }
}
