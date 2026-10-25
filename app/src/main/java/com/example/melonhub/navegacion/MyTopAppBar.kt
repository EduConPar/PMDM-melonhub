package com.example.melonhub.navegacion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.melonhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(
                text = when (currentRoute) {
                    Rutas.HOME -> "Peliculas Home"
                    Rutas.DETALLE -> "Detalle"
                    Rutas.PERFIL -> "Mi perfil"
                    Rutas.RESEÑAS -> "Reseñas"
                    else -> ""
                },
                style = MaterialTheme.typography.headlineLarge
            )
        },
        navigationIcon = {
            if (currentRoute != Rutas.HOME) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = "Volver")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}
