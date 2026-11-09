package com.example.melonhub.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.melonhub.model.pelicula
import com.example.melonhub.model.reseña
import com.example.melonhub.ui.screen.AñadirReseña
import com.example.melonhub.ui.screen.DetalleScreen
import com.example.melonhub.ui.screen.HomeScreen
import com.example.melonhub.ui.screen.PeliculaDetalleScreen
import com.example.melonhub.ui.screen.PerfilScreen
import com.example.melonhub.ui.screen.ReseñasScreen

object Rutas {
    const val HOME = "home"
    const val DETALLE = "detalle"
    const val RESEÑAS = "peliculas"
    const val PERFIL = "perfil"
    const val PELICULA = "pelicula"
    const val AÑADIR = "añadir"
    const val EDITAR_RESEÑA = "editar_reseña"

    fun detalleConId(reseñaId: Long) = "$DETALLE/$reseñaId"
    fun peliculaConId(peliculaId: Long) = "$PELICULA/$peliculaId"
    fun editarReseñaConId(reseñaId: Long) = "$EDITAR_RESEÑA/$reseñaId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val p1 = pelicula(1, "Peli 1", 148, null)
    val p2 = pelicula(2, "Peli 2", 169, null)
    val p3 = pelicula(3, "Peli 3", 152, null)

    val r1 = reseña(1, 5.0, "Excelente película, los efectos visuales son increíbles.", pelicula = p1)
    val r2 = reseña(2, 4.0, "Muy entretenida, aunque el final es algo apresurado.", pelicula = p2)
    val r3 = reseña(3, 3.0, "Aceptable para pasar el rato.", pelicula = p1)
    val r4 = reseña(4, 5.0, "Una obra maestra del cine contemporáneo.", pelicula = p3)

    p1.reseñas = listOf(r1, r3)
    p2.reseñas = listOf(r2)
    p3.reseñas = listOf(r4)

    val peliculas = remember { mutableStateListOf(p1, p2, p3) }
    val todasLasReseñas = remember { mutableStateListOf(r1, r2, r3, r4) }
    val reseñasInicio = remember { mutableStateListOf(r1, r2, r4) }
    val reseñasDestacadas = remember { mutableStateListOf(r1, r4) }

    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        composable(Rutas.HOME) {
            HomeScreen(
                modifier = Modifier,
                reseñas = reseñasInicio,
                onReseñaClick = { navController.navigate(Rutas.detalleConId(it)) },
                onAñadirClick = { navController.navigate(Rutas.AÑADIR) }
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
                reseña = buscarReseña(id, todasLasReseñas),
                onEditClick = { navController.navigate(Rutas.editarReseñaConId(id)) }
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

        composable(Rutas.AÑADIR) {
            AñadirReseña(
                modifier = Modifier,
                reseñaInicial = null,
                peliculas = peliculas,
                onSaveClick = { nuevaReseña ->
                    val tituloBusqueda = nuevaReseña.titulo.trim()
                    var peliculaMatch = peliculas.find { it.titulo.equals(tituloBusqueda, ignoreCase = true) }
                    if (peliculaMatch == null) {
                        peliculaMatch = pelicula(
                            (peliculas.maxOfOrNull { it.id } ?: 0L) + 1L,
                            tituloBusqueda,
                            nuevaReseña.duracion,
                            nuevaReseña.imagen,
                            mutableListOf()
                        )
                        peliculas.add(peliculaMatch)
                    }

                    val reseñaFinal = reseña(
                        id = (todasLasReseñas.maxOfOrNull { it.id } ?: 0L) + 1L,
                        puntuacion = nuevaReseña.puntuacion,
                        descripcion = nuevaReseña.descripcion,
                        usuario = nuevaReseña.usuario,
                        pelicula = peliculaMatch,
                        directTitulo = nuevaReseña.titulo,
                        directDuracion = nuevaReseña.duracion,
                        directImagen = nuevaReseña.imagen
                    )
                    peliculaMatch.reseñas.add(reseñaFinal)
                    todasLasReseñas.add(reseñaFinal)
                    reseñasInicio.add(reseñaFinal)

                    navController.popBackStack()
                },
                onCancelarClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Rutas.EDITAR_RESEÑA}/{reseñaId}",
            arguments = listOf(navArgument("reseñaId") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("reseñaId") ?: 1L
            val reseñaAEditar = buscarReseña(id, todasLasReseñas)
            AñadirReseña(
                modifier = Modifier,
                reseñaInicial = reseñaAEditar,
                peliculas = peliculas,
                onSaveClick = { reseñaModificada ->
                    val tituloBusqueda = reseñaModificada.titulo.trim()
                    var peliculaMatch = peliculas.find { it.titulo.equals(tituloBusqueda, ignoreCase = true) }
                    if (peliculaMatch == null) {
                        peliculaMatch = pelicula(
                            (peliculas.maxOfOrNull { it.id } ?: 0L) + 1L,
                            tituloBusqueda,
                            reseñaModificada.duracion,
                            reseñaModificada.imagen,
                            mutableListOf()
                        )
                        peliculas.add(peliculaMatch)
                    }

                    val index = todasLasReseñas.indexOfFirst { r -> r.id == id }
                    val reseñaFinal = reseña(
                        id = id,
                        puntuacion = reseñaModificada.puntuacion,
                        descripcion = reseñaModificada.descripcion,
                        usuario = reseñaModificada.usuario,
                        pelicula = peliculaMatch,
                        directTitulo = reseñaModificada.titulo,
                        directDuracion = reseñaModificada.duracion,
                        directImagen = reseñaModificada.imagen
                    )
                    if (index != -1) {
                        todasLasReseñas[index] = reseñaFinal
                    }

                    val indexInicio = reseñasInicio.indexOfFirst { r -> r.id == id }
                    if (indexInicio != -1) {
                        reseñasInicio[indexInicio] = reseñaFinal
                    }

                    val indexDestacada = reseñasDestacadas.indexOfFirst { r -> r.id == id }
                    if (indexDestacada != -1) {
                        reseñasDestacadas[indexDestacada] = reseñaFinal
                    }

                    if (!peliculaMatch.reseñas.contains(reseñaFinal)) {
                        peliculaMatch.reseñas.removeIf { r -> r.id == id }
                        peliculaMatch.reseñas.add(reseñaFinal)
                    }

                    navController.popBackStack()
                },
                onCancelarClick = {
                    navController.popBackStack()
                }
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
