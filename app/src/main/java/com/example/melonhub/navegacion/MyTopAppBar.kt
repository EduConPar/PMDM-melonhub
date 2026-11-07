package com.example.melonhub.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.melonhub.R
import com.example.melonhub.ui.theme.MelonhubTheme
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPoster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        modifier = Modifier
            .background(RetroPoster)
            .statusBarsPadding(),
        title = {
            Text(
                text = when (currentRoute) {
                    Rutas.HOME -> "Peliculas Home"
                    Rutas.DETALLE -> "Detalle"
                    Rutas.PERFIL -> "Mi perfil"
                    Rutas.RESEÑAS -> "Peliculas"
                    else -> ""

                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
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
            containerColor = Color.Transparent,
            titleContentColor = RetroNegro,
            navigationIconContentColor = RetroNegro
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppTopBarPreview() {
    MelonhubTheme {
        MyAppTopBar(navController = rememberNavController())
    }
}
