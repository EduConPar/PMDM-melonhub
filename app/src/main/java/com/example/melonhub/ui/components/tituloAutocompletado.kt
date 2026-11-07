package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.example.melonhub.busqueda.PeliculaBuscador
import com.example.melonhub.model.pelicula
import kotlinx.coroutines.delay

private const val RETARDO_BUSQUEDA_MS = 300L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tituloAutocompletado(
    value: String,
    onValueChange: (String) -> Unit,
    peliculas: List<pelicula>,
    onPeliculaSeleccionada: (pelicula) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null
) {
    var sugerencias by remember { mutableStateOf(emptyList<pelicula>()) }
    var expandido by remember { mutableStateOf(false) }
    var conFoco by remember { mutableStateOf(false) }

    var tituloAplicado by remember { mutableStateOf<String?>(value) }

    LaunchedEffect(value) {
        if (value == tituloAplicado) {
            expandido = false
            return@LaunchedEffect
        }
        delay(RETARDO_BUSQUEDA_MS)
        sugerencias = PeliculaBuscador.buscarSimilares(value, peliculas)
        expandido = conFoco && sugerencias.isNotEmpty()
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expandido,
        onExpandedChange = { expandido = it && sugerencias.isNotEmpty() }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
                .onFocusChanged {
                    conFoco = it.isFocused
                    if (!it.isFocused) expandido = false
                },
            value = value,
            onValueChange = {
                tituloAplicado = null
                onValueChange(it)
            },
            label = label,
            singleLine = true,
            isError = isError,
            supportingText = supportingText
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            sugerencias.forEach { peli ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(text = peli.titulo, style = MaterialTheme.typography.bodyLarge)
                            Text(text = "${peli.duracion} min", style = MaterialTheme.typography.bodySmall)
                        }
                    },
                    onClick = {
                        tituloAplicado = peli.titulo
                        expandido = false
                        onPeliculaSeleccionada(peli)
                    }
                )
            }
        }
    }
}
