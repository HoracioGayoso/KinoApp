package com.tpintegrador.kinoapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tpintegrador.kinoapp.model.Funcion;

import java.util.List;
@Dao
public interface funcion_dao {
    @Query("SELECT * FROM Funcion")
    LiveData<List<Funcion>> getAllFunciones();

    @Query("SELECT * FROM Funcion WHERE pelicula_id = :id")
    LiveData<List<Funcion>> getFuncionByPelicula(Integer id);
    @Insert
    void insertFuncion(Funcion funcion);

    @Update
    void updateFuncion(Funcion funcion);

    @Delete
    void deleteFuncion(Funcion funcion);
}
