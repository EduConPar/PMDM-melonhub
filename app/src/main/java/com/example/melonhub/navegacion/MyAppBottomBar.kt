package com.example.melonhub.navegacion

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.melonhub.R


@Composable
fun MyAppBottomBar (
    navController: NavHostController
){
    NavigationBar() {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = { navController.navigate(Rutas.HOME)},
            icon = { Icon(painter = painterResource(R.drawable.home_icon), contentDescription = "Home") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.RESEÑAS,
            onClick = { navController.navigate(Rutas.RESEÑAS)},
            icon = { Icon(painter = painterResource(R.drawable.lists_icon), contentDescription = "Reselas")},
            label = { Text("Reseñas")}
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = { navController.navigate(Rutas.PERFIL)},
            icon = { Icon(painter = painterResource(R.drawable.account_circle_icon), contentDescription = "Perfil")},
            label = { Text("Perfil")}
        )
    }
}