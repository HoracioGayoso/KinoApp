package com.tpintegrador.kinoapp.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tpintegrador.kinoapp.model.Pelicula;

import java.util.List;
@Dao
public interface pelicula_dao {
    @Query("SELECT * FROM Pelicula")
    LiveData<List<Pelicula>> getAllPeliculas();

    @Query("SELECT * FROM Pelicula WHERE nombre = :nombre")
    LiveData<List<Pelicula>> getPeliculaByName(String nombre);
    @Insert
    void insertPelicula(Pelicula pelicula);

    @Update
    void updatePelicula(Pelicula pelicula);

    @Delete
    void deletePelicula(Pelicula pelicula);
}
