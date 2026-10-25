package com.example.melonhub.model;

import java.util.ArrayList;
import java.util.List;

public class pelicula {
    private long id;
    private String titulo;
    private int duracion;
    private List<reseña> reseñas;

    public pelicula(long id, String titulo, int duracion, List<reseña> reseñas) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.reseñas = reseñas != null ? reseñas : new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<reseña> reseñas) {
        this.reseñas = reseñas != null ? reseñas : new ArrayList<>();
    }

    public int getNumeroReseñas() {
        return reseñas.size();
    }

    public double getPuntuacionMedia() {
        if (reseñas.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (reseña r : reseñas) {
            suma += r.getPuntuacion();
        }
        return suma / reseñas.size();
    }
}