package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.theme.MelonhubTheme

@Composable
fun peliculaList(
    modifier: Modifier = Modifier,
    peliculas: List<pelicula>,
    onPeliculaClick: (Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(
            items = peliculas,
            key = { peliculaItem -> peliculaItem.id }
        ) { peliculaItem ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                peliculaCard(
                    pelicula = peliculaItem,
                    onClick = { onPeliculaClick(peliculaItem.id) }
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PeliculaListPreview() {
    MelonhubTheme {
        peliculaList(
            peliculas = listOf(
                pelicula(
                    1L,
                    "Matrix",
                    136,
                    listOf(reseña(1L, 5.0, "Excelente película."))
                ),
                pelicula(
                    2L,
                    "El viaje de Chihiro",
                    125,
                    listOf(reseña(2L, 4.5, "Una película preciosa."))
                ),
                pelicula(
                    3L,
                    "Interestelar",
                    169,
                    listOf(reseña(3L, 5.0, "Impresionante."))
                )
            ),
            onPeliculaClick = {}
        )
    }
}
