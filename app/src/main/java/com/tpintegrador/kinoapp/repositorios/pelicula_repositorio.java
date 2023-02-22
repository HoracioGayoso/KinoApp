package com.tpintegrador.kinoapp.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tpintegrador.kinoapp.AppDatabase;
import com.tpintegrador.kinoapp.dao.pelicula_dao;
import com.tpintegrador.kinoapp.model.Pelicula;

import java.util.List;

public class pelicula_repositorio {
    private final pelicula_dao peliculaDao;
    private LiveData<List<Pelicula>> allPeliculas;

    public pelicula_repositorio(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        peliculaDao = database.peliculaDao();
        allPeliculas = peliculaDao.getAllPeliculas();
    }

    public LiveData<List<Pelicula>> getAllPeliculas() {
        return allPeliculas;
    }

    public LiveData<List<Pelicula>> getPeliculaByName(String nombre) {
        return peliculaDao.getPeliculaByName(nombre);
    }

    public void insert(Pelicula pelicula) {
        new InsertPeliculaAsyncTask(peliculaDao).execute(pelicula);
    }

    public void update(Pelicula pelicula) {
        new UpdatePeliculaAsyncTask(peliculaDao).execute(pelicula);
    }

    public void delete(Pelicula pelicula) {
        new DeletePeliculaAsyncTask(peliculaDao).execute(pelicula);
    }

    private static class InsertPeliculaAsyncTask extends AsyncTask<Pelicula, Void, Void> {
        private final pelicula_dao peliculaDao;

        private InsertPeliculaAsyncTask(pelicula_dao peliculaDao) {
            this.peliculaDao = peliculaDao;
        }

        @Override
        protected Void doInBackground(Pelicula... peliculas) {
            peliculaDao.insertPelicula(peliculas[0]);
            return null;
        }
    }

    private static class UpdatePeliculaAsyncTask extends AsyncTask<Pelicula, Void, Void> {
        private final pelicula_dao peliculaDao;

        private UpdatePeliculaAsyncTask(pelicula_dao peliculaDao) {
            this.peliculaDao = peliculaDao;
        }

        @Override
        protected Void doInBackground(Pelicula... peliculas) {
            peliculaDao.updatePelicula(peliculas[0]);
            return null;
        }
    }

    private static class DeletePeliculaAsyncTask extends AsyncTask<Pelicula, Void, Void> {
        private final pelicula_dao peliculaDao;

        private DeletePeliculaAsyncTask(pelicula_dao peliculaDao) {
            this.peliculaDao = peliculaDao;
        }

        @Override
        protected Void doInBackground(Pelicula... peliculas) {
            peliculaDao.deletePelicula(peliculas[0]);
            return null;
        }
    }
}
