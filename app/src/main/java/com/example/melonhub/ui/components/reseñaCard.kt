package com.example.melonhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.melonhub.R
import com.example.melonhub.model.reseña


@Composable
fun reseñaCard (reseña: reseña, modifier: Modifier){
    Card (modifier = modifier) {
        Row (modifier = modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(modifier = Modifier.size(64.dp), painter = painterResource(R.drawable.image_mockup), contentDescription = "Imagen Receta")
            Column() {
                Text(text = reseña.titulo, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                    for (i in 1..reseña.puntuacion) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.star_mockup),
                            contentDescription = "Puntuación"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "(${reseña.reseñas})", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}