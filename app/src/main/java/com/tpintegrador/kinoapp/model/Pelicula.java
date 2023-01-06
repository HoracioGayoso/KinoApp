package com.tpintegrador.kinoapp.model;

public class Pelicula {
    Integer id;
    String nombre;
    String año;
    Integer imagen;

    public Pelicula(){}

    public Pelicula(Integer id, String nombre, String año, Integer imagen) {
        this.id = id;
        this.nombre = nombre;
        this.año = año;
       //this.imagen = imagen;
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

    /*public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }*/
}
