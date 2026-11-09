package com.example.melonhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroFondo
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto

@Composable
fun reseñaCard(
    reseña: reseña,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(48.dp)
                .height(68.dp)
                .background(RetroPoster)
                .win98(),
            painter = painterResource(R.drawable.image_mockup),
            contentDescription = "Póster ${reseña.titulo}",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = reseña.pelicula?.titulo ?: "Película",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = RetroTexto,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                puntuacionEstrellas(puntuacion = reseña.puntuacion)
                Text(
                    text = "  ·  ${reseña.usuario}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroAmarillo
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1C1C1C)
@Composable
fun ReseñaCardPreview() {
    MelonhubTheme {
        Surface(color = RetroFondo) {
            reseñaCard(
                reseña = reseña(
                    id = 1L,
                    puntuacion = 5.0,
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
}
