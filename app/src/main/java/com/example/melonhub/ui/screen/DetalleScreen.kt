package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.win98
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto

@Composable
fun DetalleScreen(
    modifier: Modifier = Modifier,
    reseña: reseña,
    onEditClick: () -> Unit = {}
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
                    text = reseña.titulo.ifEmpty { "Película" },
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
                        text = ": ${reseña.duracion} min",
                        style = MaterialTheme.typography.headlineMedium,
                        color = RetroAmarillo
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.star_mockup),
                        contentDescription = "Puntuación",
                        colorFilter = ColorFilter.tint(RetroAmarillo)
                    )

                    Text(
                        text = ": ${String.format(java.util.Locale.US, "%.1f", reseña.puntuacion / 2.0)}",
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reseña.descripcion,
                    style = MaterialTheme.typography.bodyLarge,
                    color = RetroTexto
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onEditClick
        ) {
            Text(text = "Editar reseña")
        }
    }
}

@Preview
@Composable
fun DetalleScreenPreview() {
    MelonhubTheme {
        DetalleScreen(
            modifier = Modifier,
            reseña = reseña(
                id = 1,
                puntuacion = 10.0,
                descripcion = "La clásica película de acción y ciencia ficción."
            ),
            onEditClick = {}
        )
    }
}
