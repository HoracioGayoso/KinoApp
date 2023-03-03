package com.tpintegrador.kinoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Foro",
        foreignKeys = @ForeignKey(entity = Pelicula.class,
        parentColumns = "id",
        childColumns = "pelicula_id"),
        indices = @Index("pelicula_id"))
public class  Foro {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "pelicula_id")
    Integer pelicula;
    @ColumnInfo(name = "categoria")
    Categorias_foro categoria;

    public Foro(Integer pelicula, Categorias_foro categoria) {
        this.pelicula = pelicula;
        this.categoria = categoria;
    }

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

    public Categorias_foro getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias_foro categoria) {
        this.categoria = categoria;
    }
}
