package com.tpintegrador.kinoapp.model;

public class Pelicula {
    Integer id;
    String nombre;
    String año;
    int imagen;

    public Pelicula(){}

    public Pelicula(Integer id, String nombre, String año, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
