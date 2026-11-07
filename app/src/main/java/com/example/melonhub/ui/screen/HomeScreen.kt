package com.example.melonhub.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.puntuacionEstrellas
import com.example.melonhub.ui.components.win98
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroPoster
import com.example.melonhub.ui.theme.RetroTexto
import com.example.melonhub.ui.theme.RetroTextoTenue
import com.example.melonhub.ui.theme.TipografiaRetro

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit,
    onAñadirClick: () -> Unit = {}
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = TipografiaRetro
    ) {
        ContenidoHome(
            modifier = modifier,
            reseñas = reseñas,
            onReseñaClick = onReseñaClick,
            onAñadirClick = onAñadirClick
        )
    }
}

@Composable
private fun ContenidoHome(
    modifier: Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit,
    onAñadirClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RetroPoster)
            .padding(8.dp)
    ) {
        CabeceraPerfil(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(RetroNegro)
                .win98(true, 2.dp)


        ) {
            itemsIndexed(reseñas, key = { _, r -> r.id }) { index, r ->
                FilaReseña(
                    reseña = r,
                    onClick = { onReseñaClick(r.id) }
                )
                if (index < reseñas.lastIndex) {
                    SeparadorPunteado()
                }
            }
            item {
                BotonAñadir(onClick = onAñadirClick)
            }
        }
    }
}

@Composable
private fun CabeceraPerfil(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(RetroPanel)
            .win98(grosor = 2.dp)
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            modifier = Modifier
                .size(72.dp)
                .win98(hundido = true)
                .padding(horizontal = 2.dp, 2.dp),
            painter = painterResource(R.drawable.capi),
            contentDescription = "Foto perfil",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Perfil de",
                style = MaterialTheme.typography.bodySmall,
                color = RetroTextoTenue
            )
            Text(
                text = "Edu",
                style = MaterialTheme.typography.headlineSmall,
                color = RetroTexto
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "\"Lorem ipsum dolor sit amet consectetur adipiscing elit metus donec nullam aliquam elementum, duis penatibus dignissim sagittis dui vel inceptos commodo senectus feugiat porta\"",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = RetroAmarillo
            )
        }
    }
}

@Composable
private fun FilaReseña(
    reseña: reseña,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(48.dp)
                .height(68.dp)
                .background(RetroPoster)
                .win98(),
            painter = painterResource(R.drawable.image_mockup),
            contentDescription = "Póster ${reseña.titulo}",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = reseña.pelicula?.titulo ?: "Película",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = RetroTexto,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                puntuacionEstrellas(puntuacion = reseña.puntuacion)
                Text(
                    text = "  ·  ${reseña.usuario}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = RetroAmarillo
                )
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

@Composable
private fun BotonAñadir(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "+",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = RetroAmarillo
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Añadir película",
            style = MaterialTheme.typography.bodyLarge,
            color = RetroTexto.copy(alpha = 0.8f)
        )
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
