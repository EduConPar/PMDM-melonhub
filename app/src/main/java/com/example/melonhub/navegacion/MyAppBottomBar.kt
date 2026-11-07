package com.example.melonhub.navegacion

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.melonhub.R
import com.example.melonhub.ui.components.win98
import com.example.melonhub.ui.theme.RetroAmarillo
import com.example.melonhub.ui.theme.RetroNegro
import com.example.melonhub.ui.theme.RetroPanel
import com.example.melonhub.ui.theme.RetroTextoTenue


@Composable
fun MyAppBottomBar (
    navController: NavHostController
){
    val coloresItem = NavigationBarItemDefaults.colors(
        selectedIconColor = RetroNegro,
        selectedTextColor = RetroAmarillo,
        indicatorColor = RetroAmarillo,
        unselectedIconColor = RetroTextoTenue,
        unselectedTextColor = RetroTextoTenue
    )

    NavigationBar(
        modifier = Modifier.win98(grosor = 2.dp, reservarEspacio = false),
        containerColor = RetroPanel
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { navController.navigate(Rutas.HOME)},
            icon = { Icon(painter = painterResource(R.drawable.home_icon), contentDescription = "Home") },
            label = { Text("Home") },
            colors = coloresItem
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.RESEÑAS,
            onClick = { navController.navigate(Rutas.RESEÑAS)},
            icon = { Icon(painter = painterResource(R.drawable.lists_icon), contentDescription = "Reselas")},
            label = { Text("Peliculas")},
            colors = coloresItem
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL)},
            icon = { Icon(painter = painterResource(R.drawable.account_circle_icon), contentDescription = "Perfil")},
            label = { Text("Perfil")},
            colors = coloresItem
        )
    }
}
