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
import com.example.melonhub.ui.components.peliculaList

@Composable
fun ReseñasScreen(
    modifier: Modifier = Modifier,
    peliculas: List<pelicula>,
    onPeliculaClick: (Long) -> Unit
) {
    Surface {
        Column(modifier = modifier) {
            Text(text = "TODAS LAS RESEÑAS", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(2.dp))
            peliculaList(
                modifier = Modifier.fillMaxWidth(),
                peliculas = peliculas,
                onPeliculaClick = { onPeliculaClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReseñasScreenPreview() {
    val r1 = reseña(1, 5, "Muy Buena", "Edu")
    val peliculas = listOf(
        pelicula(1, "Peli 1", 120, listOf(r1))
    )

    ReseñasScreen(
        modifier = Modifier,
        peliculas = peliculas,
        onPeliculaClick = { }
    )
}
