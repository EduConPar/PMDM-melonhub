package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.reseña

@Composable
fun reseñasList(
    modifier: Modifier,
    reseñas: List<reseña>
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items (count = 20){
            reseñaCard(
                modifier = Modifier.fillMaxWidth(),
                reseña = reseñas.random()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun reseñasListPreview() {
    reseñasList(
        modifier = Modifier,
        reseñas = listOf(
            reseña(titulo = "Peli 1", duracion = 120, puntuacion = 5, descripcion = "Muy Buena", reseñas = 2),
            reseña(titulo = "Peli 2", duracion = 120, puntuacion = 4, descripcion = "Muy Buena", reseñas =  1)
        )
    )
}