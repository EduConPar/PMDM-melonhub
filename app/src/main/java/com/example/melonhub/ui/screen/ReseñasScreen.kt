package com.example.melonhub.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.peliculaList
import com.example.melonhub.ui.components.win98
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPoster

@Composable
fun ReseñasScreen(
    modifier: Modifier = Modifier,
    peliculas: List<pelicula>,
    onPeliculaClick: (Long) -> Unit
) {
    Surface(color = RetroPoster) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(RetroNegro)
                .win98(hundido = true, grosor = 2.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "TODAS LAS RESEÑAS",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = RetroAmarillo
            )
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

    MelonhubTheme {
        ReseñasScreen(
            modifier = Modifier,
            peliculas = peliculas,
            onPeliculaClick = { }
        )
    }
}
