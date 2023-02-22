package com.tpintegrador.kinoapp;



import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.tpintegrador.kinoapp.dao.foro_dao;
import com.tpintegrador.kinoapp.dao.funcion_dao;
import com.tpintegrador.kinoapp.dao.pelicula_dao;
import com.tpintegrador.kinoapp.dao.publicacion_dao;
import com.tpintegrador.kinoapp.dao.usuario_dao;
import com.tpintegrador.kinoapp.model.Foro;
import com.tpintegrador.kinoapp.model.Funcion;
import com.tpintegrador.kinoapp.model.Pelicula;
import com.tpintegrador.kinoapp.model.Publicacion_foro;
import com.tpintegrador.kinoapp.model.Usuario;

@Database(entities = {Usuario.class, Publicacion_foro.class, Pelicula.class, Funcion.class,
                        Foro.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "kinoapp_databse").build();
        }
        return instance;
    }

    public abstract usuario_dao usuarioDao();
    public abstract pelicula_dao peliculaDao();
    public abstract funcion_dao funcionDao();
    public abstract publicacion_dao publicaionDao();
    public abstract foro_dao foroDao();
}
