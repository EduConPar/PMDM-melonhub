package com.example.melonhub.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.screen.DetalleScreen
import com.example.melonhub.ui.screen.HomeScreen
import com.example.melonhub.ui.screen.PeliculaDetalleScreen
import com.example.melonhub.ui.screen.PerfilScreen
import com.example.melonhub.ui.screen.ReseñasScreen

object Rutas {
    const val HOME = "home"
    const val DETALLE = "detalle"
    const val RESEÑAS = "reseñas"
    const val PERFIL = "perfil"
    const val PELICULA = "pelicula"

    fun detalleConId(reseñaId: Long) = "$DETALLE/$reseñaId"
    fun peliculaConId(peliculaId: Long) = "$PELICULA/$peliculaId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val p1 = pelicula(1, "Peli 1", 148, null)
    val p2 = pelicula(2, "Peli 2", 169, null)
    val p3 = pelicula(3, "Peli 3", 152, null)

    val r1 = reseña(1, 5, "Excelente película, los efectos visuales son increíbles.", pelicula = p1)
    val r2 = reseña(2, 4, "Muy entretenida, aunque el final es algo apresurado.", pelicula = p2)
    val r3 = reseña(3, 3, "Aceptable para pasar el rato.", pelicula = p1)
    val r4 = reseña(4, 5, "Una obra maestra del cine contemporáneo.", pelicula = p3)

    p1.reseñas = listOf(r1, r3)
    p2.reseñas = listOf(r2)
    p3.reseñas = listOf(r4)

    val peliculas = listOf(p1, p2, p3)
    val todasLasReseñas = listOf(r1, r2, r3, r4)
    val reseñasInicio = listOf(r1, r2, r4)
    val reseñasDestacadas = listOf(r1, r4)

    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        composable(Rutas.HOME) {
            HomeScreen(
                modifier = Modifier,
                reseñas = reseñasInicio,
                onReseñaClick = { navController.navigate(Rutas.detalleConId(it)) }
            )
        }
        composable(Rutas.RESEÑAS) {
            ReseñasScreen(
                modifier = Modifier,
                peliculas = peliculas,
                onPeliculaClick = { navController.navigate(Rutas.peliculaConId(it)) }
            )
        }
        composable(Rutas.PERFIL) {
            PerfilScreen(
                modifier = Modifier,
                reseñas = reseñasDestacadas,
                onReseñaClick = { navController.navigate(Rutas.detalleConId(it)) }
            )
        }

        composable(
            route = "${Rutas.DETALLE}/{reseñaId}",
            arguments = listOf(navArgument("reseñaId") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("reseñaId") ?: 1L
            DetalleScreen(
                modifier = Modifier,
                reseña = buscarReseña(id, todasLasReseñas)
            )
        }

        composable(
            route = "${Rutas.PELICULA}/{peliculaId}",
            arguments = listOf(navArgument("peliculaId") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("peliculaId") ?: 1L
            PeliculaDetalleScreen(
                modifier = Modifier,
                pelicula = buscarPelicula(id, peliculas),
                onReseñaClick = { reseñaId -> navController.navigate(Rutas.detalleConId(reseñaId)) }
            )
        }
    }
}

fun buscarPelicula(id: Long, lista: List<pelicula>): pelicula {
    return lista.find { it.id == id } ?: lista.first()
}

fun buscarReseña(id: Long, lista: List<reseña>): reseña {
    return lista.find { it.id == id } ?: lista.first()
}
