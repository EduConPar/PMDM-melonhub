package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.reseña

@Composable
fun DetalleScreen(
    modifier: Modifier = Modifier,
    reseña: reseña
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
                    text = reseña.titulo.ifEmpty { "Película" },
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

                    Text(text = ": ${reseña.duracion} min", style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.star_mockup),
                        contentDescription = "Puntuación"
                    )

                    Text(text = ": ${reseña.puntuacion}", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reseña.descripcion,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun DetalleScreenPreview() {
    DetalleScreen(
        modifier = Modifier,
        reseña = reseña(
            id = 1,
            puntuacion = 5,
            descripcion = "La clásica película de acción y ciencia ficción."
        )
    )
}
