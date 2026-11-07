package com.example.melonhub.validacion

object ReseñaFormValidator {
    fun validarTitulo(titulo: String): String? {
        return if (titulo.isBlank()) {
            "El titulo es obligatorio"
        } else { null }
    }
    fun validarDuracion(duracion: String): String? {
        val minutos = duracion.toIntOrNull()
        return when {
            minutos == null -> "Introduce un número válido"
            minutos <= 0 -> "La duracion debe ser mayor que cero"
            else -> null
        }
    }
}