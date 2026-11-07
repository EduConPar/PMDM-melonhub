package com.example.melonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.reseña

@Composable
fun reseñasList(
    modifier: Modifier = Modifier,
    reseñas: List<reseña>,
    onReseñaClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reseñas) { reseñaItem ->
            reseñaCard(
                modifier = Modifier.fillMaxWidth(),
                reseña = reseñaItem,
                onClick = { onReseñaClick(reseñaItem.id) }
            )
        }
    }
}
