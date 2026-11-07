package com.example.melonhub.model

class reseña(
    val id: Long,
    val puntuacion: Int,
    val descripcion: String,
    val usuario: String = "Usuario",
    val pelicula: pelicula? = null,
    private val directTitulo: String = "",
    val directDuracion: Int = 0,
    val directImagen: String = ""
) {
    val titulo: String get() = pelicula?.titulo ?: directTitulo
    val duracion: Int get() = pelicula?.duracion ?: directDuracion
    val imagen: String get() = pelicula?.imagen ?: directImagen
}
