package com.tpintegrador.kinoapp.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tpintegrador.kinoapp.model.Usuario;

import java.util.List;
@Dao
public interface usuario_dao {
    @Query("SELECT * FROM Usuario")
    LiveData<List<Usuario>> getAllUsuarios();

    @Query("SELECT * FROM Usuario WHERE correo = :correo")
    LiveData<List<Usuario>> getUsuarioByCorreo(String correo);
    @Insert
    void insertUsuario(Usuario usuario);

    @Update
    void updateUsuario(Usuario usuario);

    @Delete
    void deleteUsuario(Usuario usuario);
}
