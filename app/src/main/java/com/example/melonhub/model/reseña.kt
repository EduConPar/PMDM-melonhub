package com.example.melonhub.model

class reseña(
    val id: Long,
    val puntuacion: Int,
    val descripcion: String,
    val usuario: String = "Usuario",
    val pelicula: pelicula? = null
) {
    val titulo: String get() = pelicula?.titulo ?: ""
    val duracion: Int get() = pelicula?.duracion ?: 0
}
