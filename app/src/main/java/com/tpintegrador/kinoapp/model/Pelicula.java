package com.tpintegrador.kinoapp.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Pelicula",
        indices = {@Index(value = "nombre", unique = true)})
public class Pelicula implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    Integer id;

    @NonNull
    @ColumnInfo(name = "nombre")
    String nombre;

    @ColumnInfo(name = "año")
    String año;

    @ColumnInfo(name = "imagen_id")
    int imagen;

    @ColumnInfo(name = "sinopsis")
    String sinopsis;

    @Ignore
    public Pelicula(){}

    public Pelicula(String nombre, String año, int imagen, String sinopsis) {
        this.nombre = nombre;
        this.año = año;
        this.imagen = imagen;
        this.sinopsis = sinopsis;
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

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
