package com.example.melonhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.components.mainScreen
import com.example.melonhub.ui.theme.MelonhubTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MelonhubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    mainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(8.dp),
                        reseñas = listOf(
                            reseña(titulo = "Peli 1", duracion = 120, puntuacion = 5, descripcion = "Muy Buena", reseñas = 2),
                            reseña(titulo = "Peli 2", duracion = 120, puntuacion = 4, descripcion = "Muy Buena", reseñas =  1),
                            reseña(titulo = "Peli 3", duracion = 120, puntuacion = 2, descripcion = "Mala", reseñas =  6)
                        )
                    )
                }
            }
        }
    }
}