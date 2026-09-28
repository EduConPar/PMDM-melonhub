package com.example.melonhub.model

class reseña {
    val puntuacion: Int
    val titulo: String
    val duracion: Int
    val descripcion: String
    val reseñas: Int

    constructor(puntuacion: Int, titulo: String, duracion: Int, descripcion: String, reseñas: Int) {
        this.puntuacion = puntuacion
        this.titulo = titulo
        this.duracion = duracion
        this.descripcion = descripcion
        this.reseñas = reseñas
    }
}
