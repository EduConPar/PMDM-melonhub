package com.example.melonhub.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.tituloAutocompletado
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroTexto
import com.example.melonhub.ui.theme.RetroTextoTenue
import com.example.melonhub.validacion.ReseñaFormValidator

@Composable
fun AñadirReseña(
    modifier: Modifier = Modifier,
    reseñaInicial: reseña? = null,
    peliculas: List<pelicula> = emptyList(),
    onSaveClick: (reseña) -> Unit,
    onCancelarClick: () -> Unit
) {
    var titulo by remember { mutableStateOf(reseñaInicial?.titulo ?: "") }
    var imagen by remember { mutableStateOf(reseñaInicial?.imagen ?: "") }
    var duracion by remember { mutableStateOf(reseñaInicial?.duracion?.toString() ?: "") }
    var puntuacion by remember { mutableDoubleStateOf(reseñaInicial?.puntuacion ?: 0.0) }
    var descripcion by remember { mutableStateOf(reseñaInicial?.descripcion ?: "") }
    var errorTitulo by remember { mutableStateOf<String?>(null) }
    var errorDuracion by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            tituloAutocompletado(
                modifier = Modifier.fillMaxWidth(),
                value = titulo,
                onValueChange = { titulo = it },
                peliculas = peliculas,
                onPeliculaSeleccionada = { peli ->
                    titulo = peli.titulo
                    duracion = peli.duracion.toString()
                    imagen = peli.imagen ?: ""
                    errorTitulo = null
                    errorDuracion = null
                },
                label = { Text("Titulo") },
                isError = errorTitulo != null,
                supportingText = { errorTitulo?.let { Text(text = it, color = MaterialTheme.colorScheme.error) } }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = imagen,
                onValueChange = { imagen = it },
                label = { Text("Imagen (URL, opcional)") },
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duracion (min)") },
                isError = errorDuracion != null,
                supportingText = { errorDuracion?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }}
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Puntuación:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = RetroTexto
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        val estrellaCompleta = puntuacion >= i
                        val mediaEstrella = !estrellaCompleta && puntuacion >= i - 0.5

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .pointerInput(i) {
                                    detectTapGestures { posicion ->
                                        puntuacion = if (posicion.x < size.width / 2f) {
                                            i - 0.5
                                        } else {
                                            i.toDouble()
                                        }
                                    }
                                },
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Image(
                                modifier = Modifier.size(36.dp),
                                painter = painterResource(R.drawable.star_mockup),
                                contentDescription = if (mediaEstrella) "Media estrella $i" else "Estrella $i",
                                colorFilter = ColorFilter.tint(
                                    if (estrellaCompleta || mediaEstrella) RetroAmarillo
                                    else RetroTextoTenue
                                )
                            )
                            if (mediaEstrella) {
                                Box(
                                    modifier = Modifier
                                        .size(width = 18.dp, height = 36.dp)
                                        .clipToBounds()
                                ) {
                                    Image(
                                        modifier = Modifier.size(36.dp),
                                        painter = painterResource(R.drawable.star_mockup),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(RetroTextoTenue)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onCancelarClick
            ) {
                Text(text = "Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    errorTitulo = ReseñaFormValidator.validarTitulo(titulo)
                    errorDuracion = ReseñaFormValidator.validarDuracion(duracion)
                    if (errorTitulo == null && errorDuracion == null) {
                        onSaveClick(
                            reseña(
                                id = reseñaInicial?.id ?: 0,
                                puntuacion = puntuacion,
                                descripcion = descripcion,
                                usuario = "Capi",
                                pelicula = null,
                                directTitulo = titulo,
                                directDuracion = duracion.toIntOrNull() ?: 0,
                                directImagen = imagen
                            )
                        )
                    }

                }
            ) {
                Text(text = "Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AñadirReseñaPreview() {
    MelonhubTheme {
        AñadirReseña(
            modifier = Modifier,
            reseñaInicial = reseña(
                id = 1,
                puntuacion = 4.0,
                descripcion = "Una película excelente con una trama fascinante y grandes actuaciones.",
                pelicula = pelicula(1, "Matrix", 136, null)
            ),
            onSaveClick = {},
            onCancelarClick = {}
        )
    }
}
