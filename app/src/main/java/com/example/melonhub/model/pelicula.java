package com.example.melonhub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class pelicula {
    private long id;
    private String titulo;
    private int duracion;
    private String imagen;
    private List<reseña> reseñas;

    public pelicula(long id, String titulo, int duracion, String imagen, List<reseña> reseñas) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.imagen = imagen != null ? imagen : "";
        this.reseñas = reseñas != null ? new ArrayList<>(reseñas) : new ArrayList<>();
    }

    public pelicula(long id, String titulo, int duracion, List<reseña> reseñas) {
        this(id, titulo, duracion, "", reseñas);
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
        if (reseñas == null || reseñas.isEmpty()) {
            return duracion;
        }
        Map<Integer, Integer> counts = new HashMap<>();
        for (reseña r : reseñas) {
            int d = r.getDirectDuracion();
            if (d > 0) {
                counts.merge(d, 1, Integer::sum);
            }
        }
        if (counts.isEmpty()) {
            return duracion;
        }
        int mostFrequentDuration = duracion;
        int maxCount = -1;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentDuration = entry.getKey();
            }
        }
        return mostFrequentDuration;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getImagen() {
        if (reseñas == null || reseñas.isEmpty()) {
            return imagen;
        }
        Map<String, Integer> counts = new HashMap<>();
        for (reseña r : reseñas) {
            String img = r.getDirectImagen();
            if (img != null && !img.isEmpty()) {
                counts.merge(img, 1, Integer::sum);
            }
        }
        if (counts.isEmpty()) {
            return imagen;
        }
        String mostFrequentImage = imagen;
        int maxCount = -1;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentImage = entry.getKey();
            }
        }
        return mostFrequentImage;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<reseña> reseñas) {
        this.reseñas = reseñas != null ? new ArrayList<>(reseñas) : new ArrayList<>();
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
