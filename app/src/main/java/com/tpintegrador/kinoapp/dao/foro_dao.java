package com.tpintegrador.kinoapp.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tpintegrador.kinoapp.model.Categorias_foro;
import com.tpintegrador.kinoapp.model.Foro;


import java.util.List;
@Dao
public interface foro_dao {
    @Query("SELECT * FROM Foro")
    LiveData<List<Foro>> getAllForos();

    @Query("SELECT * FROM Foro WHERE pelicula_id = :id AND categoria = :categoria")
    LiveData<List<Foro>> getForoByPeliculaYCategoria(Integer id, Categorias_foro categoria);
    @Insert
    void insertForo(Foro foro);

    @Update
    void updateForo(Foro foro);

    @Delete
    void deleteForo(Foro foro);
}
