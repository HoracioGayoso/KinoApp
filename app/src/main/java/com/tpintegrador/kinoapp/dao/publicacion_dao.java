package com.tpintegrador.kinoapp.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tpintegrador.kinoapp.model.Publicacion_foro;

import java.util.List;
@Dao
public interface publicacion_dao {

    @Query("SELECT * FROM Publicaciones")
    LiveData<List<Publicacion_foro>> getAllPublicaciones();

    @Query("SELECT * FROM Publicaciones WHERE foro_id = :id")
    LiveData<List<Publicacion_foro>> getPublicacionByForo(Integer id);
    @Insert
    void insertPublicacion(Publicacion_foro publicacion);

    @Update
    void updatePublicacion(Publicacion_foro publicacion);

    @Delete
    void deletePublicacion(Publicacion_foro publicacion);
}
