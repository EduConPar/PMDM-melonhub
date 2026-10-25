package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.reseñasUsuarioList

@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f, true),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier
                        .weight(0.25f)
                        .aspectRatio(1f)
                        .border(
                            width = 2.dp,
                            color = Color(0xFFC0C0C0),
                            shape = RectangleShape
                        ),
                    painter = painterResource(R.drawable.capi),
                    contentDescription = "Foto perfil",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(0.65f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Perfil de",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Edu",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\"",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
        }

        Surface(
            modifier = Modifier
                .weight(0.75f)
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "DESTACADAS", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                reseñasUsuarioList(
                    modifier = Modifier.fillMaxWidth(),
                    reseñas = reseñas,
                    onReseñaClick = { onReseñaClick(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    val p1 = pelicula(1, "Peli 1", 148, null)
    val reseñas = listOf(
        reseña(id = 1, puntuacion = 5, descripcion = "Excelente película", usuario = "Edu", pelicula = p1)
    )

    PerfilScreen(
        modifier = Modifier,
        reseñas = reseñas,
        onReseñaClick = { }
    )
}
