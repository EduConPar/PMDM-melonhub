package com.example.melonhub.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.reseñasList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit
) {
    Surface {
        Column(modifier = modifier) {
            Text(text = "TODAS LAS RESEÑAS", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            reseñasList(
                modifier = Modifier.fillMaxWidth(),
                reseñas = reseñas,
                onReseñaClick = { onReseñaClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val p1 = pelicula(1, "Peli 1", 148, null)
    val reseñas = listOf(
        reseña(id = 1, puntuacion = 5, descripcion = "Muy Buena", usuario = "Edu", pelicula = p1)
    )

    HomeScreen(
        modifier = Modifier,
        reseñas = reseñas,
        onReseñaClick = { }
    )
}
