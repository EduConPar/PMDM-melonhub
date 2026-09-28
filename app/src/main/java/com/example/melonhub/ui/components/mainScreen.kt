package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.reseña

@Composable
fun mainScreen (
    modifier: Modifier = Modifier,
    reseñas: List<reseña>
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "RESEÑAS", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        reseñasList(
            modifier = Modifier.fillMaxWidth(),
            reseñas = reseñas
        )
    }
}
