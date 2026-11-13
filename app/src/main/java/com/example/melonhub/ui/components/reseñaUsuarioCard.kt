package com.example.melonhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto

@Composable
fun reseñaUsuarioCard(
    reseña: reseña,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = modifier
            .background(RetroPanel)
            .win98(grosor = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = RetroPanel,
            contentColor = RetroTexto
        ),
        onClick = { onClick(reseña.id) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .background(RetroPoster)
                    .win98(hundido = true),
                painter = painterResource(R.drawable.image_mockup),
                contentDescription = "Imagen Película"
            )
            Column {
                Text(
                    text = reseña.pelicula?.titulo ?: reseña.titulo.ifEmpty { "Película" },
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroTexto
                )
                puntuacionEstrellas(reseña.puntuacion)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = reseña.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = RetroTexto
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReseñaUsuarioCardPreview() {
    MelonhubTheme {
        reseñaUsuarioCard(
            reseña = reseña(
                id = 1L,
                puntuacion = 10.0,
                descripcion = "Una obra maestra del cine de ciencia ficción.",
                pelicula = pelicula(
                    1L,
                    "Matrix",
                    136,
                    emptyList()
                )
            ),
            onClick = {}
        )
    }
}
