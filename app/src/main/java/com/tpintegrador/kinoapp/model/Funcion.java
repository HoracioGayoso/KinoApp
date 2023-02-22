package com.tpintegrador.kinoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Funcion",
        foreignKeys = @ForeignKey(entity = Pelicula.class,
        parentColumns = "id",
        childColumns = "pelicula_id"),
        indices = @Index("pelicula_id")
        )
public class Funcion {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "pelicula_id")
    Integer pelicula;
    @ColumnInfo(name = "fecha")
    String fecha;
    @ColumnInfo(name = "formato")
    Formato_pelicula formato;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPelicula() {
        return pelicula;
    }

    public void setPelicula(Integer pelicula) {
        this.pelicula = pelicula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Formato_pelicula getFormato() {
        return formato;
    }

    public void setFormato(Formato_pelicula formato) {
        this.formato = formato;
    }
}
