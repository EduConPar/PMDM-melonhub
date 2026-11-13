package com.example.melonhub.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroTextoTenue

@Composable
fun reseñasList(
    modifier: Modifier = Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit,
    footerContent: (@Composable () -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(reseñas, key = { _, r -> r.id }) { index, reseñaItem ->
            reseñaCard(
                modifier = Modifier.fillMaxWidth(),
                reseña = reseñaItem,
                onClick = { onReseñaClick(reseñaItem.id) }
            )
            if (index < reseñas.lastIndex) {
                SeparadorPunteado()
            }
        }
        if (footerContent != null) {
            item {
                footerContent()
            }
        }
    }
}

@Composable
private fun SeparadorPunteado() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = RetroTextoTenue,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 2.dp.toPx()))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReseñasListPreview() {
    MelonhubTheme {
        reseñasList(
            reseñas = listOf(
                reseña(
                    id = 1L,
                    puntuacion = 10.0,
                    descripcion = "Una obra maestra del cine de ciencia ficción.",
                    usuario = "Carlos",
                    pelicula = pelicula(
                        1L,
                        "Matrix",
                        136,
                        emptyList()
                    )
                ),
                reseña(
                    id = 2L,
                    puntuacion = 9.0,
                    descripcion = "Preciosa animación y gran historia.",
                    usuario = "Ana",
                    pelicula = pelicula(
                        2L,
                        "El viaje de Chihiro",
                        125,
                        emptyList()
                    )
                ),
                reseña(
                    id = 3L,
                    puntuacion = 8.0,
                    descripcion = "Visualmente deslumbrante.",
                    usuario = "Pedro",
                    pelicula = pelicula(
                        3L,
                        "Interestelar",
                        169,
                        emptyList()
                    )
                )
            ),
            onReseñaClick = {}
        )
    }
}

