package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.pelicula

@Composable
fun peliculaList(
    modifier: Modifier = Modifier,
    peliculas: List<pelicula>,
    onPeliculaClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(peliculas) {
            peliculaCard(
                modifier = Modifier.fillMaxWidth(),
                pelicula = peliculas.random(),
                onClick = { onPeliculaClick(it) }
            )
        }
    }
}
