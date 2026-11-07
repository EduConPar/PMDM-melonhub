package com.example.melonhub.busqueda

import com.example.melonhub.model.pelicula
import java.text.Normalizer

object PeliculaBuscador {

    private const val MIN_CARACTERES = 2

    fun buscarSimilares(texto: String, peliculas: List<pelicula>, max: Int = 5): List<pelicula> {
        val consulta = normalizar(texto)
        if (consulta.length < MIN_CARACTERES) return emptyList()

        return peliculas
            .map { it to puntuar(consulta, normalizar(it.titulo)) }
            .filter { (_, puntos) -> puntos > 0 }
            .sortedByDescending { (_, puntos) -> puntos }
            .take(max)
            .map { (peli, _) -> peli }
    }

    private fun puntuar(consulta: String, titulo: String): Int = when {
        titulo == consulta -> 100
        titulo.startsWith(consulta) -> 80
        titulo.split(" ").any { it.startsWith(consulta) } -> 60
        titulo.contains(consulta) -> 40
        distancia(consulta, titulo.take(consulta.length)) <= erroresPermitidos(consulta) -> 20
        else -> 0
    }

    private fun erroresPermitidos(consulta: String): Int = when {
        consulta.length >= 7 -> 2
        consulta.length >= 4 -> 1
        else -> 0
    }

    private fun normalizar(texto: String): String =
        Normalizer.normalize(texto.trim().lowercase(), Normalizer.Form.NFD)
            .replace(Regex("\\p{M}"), "")
            .replace(Regex("\\s+"), " ")

    private fun distancia(a: String, b: String): Int {
        var anterior = IntArray(b.length + 1) { it }
        for (i in 1..a.length) {
            val actual = IntArray(b.length + 1)
            actual[0] = i
            for (j in 1..b.length) {
                val coste = if (a[i - 1] == b[j - 1]) 0 else 1
                actual[j] = minOf(actual[j - 1] + 1, anterior[j] + 1, anterior[j - 1] + coste)
            }
            anterior = actual
        }
        return anterior[b.length]
    }
}
