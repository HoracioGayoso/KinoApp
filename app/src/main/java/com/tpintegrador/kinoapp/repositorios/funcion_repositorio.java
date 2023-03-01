package com.tpintegrador.kinoapp.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tpintegrador.kinoapp.AppDatabase;
import com.tpintegrador.kinoapp.dao.funcion_dao;
import com.tpintegrador.kinoapp.model.Funcion;

import java.util.List;

public class funcion_repositorio {
    private final funcion_dao funcionDao;
    private LiveData<List<Funcion>> allFuncionesByPelicula;

    public funcion_repositorio(Application application, Integer pelicula) {
        AppDatabase database = AppDatabase.getInstance(application);
        funcionDao = database.funcionDao();
        allFuncionesByPelicula = funcionDao.getFuncionByPelicula(pelicula);

    }

    public LiveData<List<Funcion>> allFuncionesByPelicula() {
        return allFuncionesByPelicula;
    }

    public LiveData<List<Funcion>> getForoByPeliculaYCategoria(Integer pelicula) {
        return funcionDao.getFuncionByPelicula(pelicula);
    }
    public void insert(Funcion funcion) {
        new funcion_repositorio.InsertFuncionAsyncTask(funcionDao).execute(funcion);
    }

    public void update(Funcion funcion) {
        new funcion_repositorio.UpdateFuncionAsyncTask(funcionDao).execute(funcion);
    }

    public void delete(Funcion funcion) {
        new funcion_repositorio.DeleteFuncionAsyncTask(funcionDao).execute(funcion);
    }

    private static class InsertFuncionAsyncTask extends AsyncTask<Funcion, Void, Void> {
        private final funcion_dao funcionDao;

        private InsertFuncionAsyncTask(funcion_dao funcionDao) {
            this.funcionDao = funcionDao;
        }

        @Override
        protected Void doInBackground(Funcion... funciones) {
            funcionDao.insertFuncion(funciones[0]);
            return null;
        }
    }

    private static class UpdateFuncionAsyncTask extends AsyncTask<Funcion, Void, Void> {
        private final funcion_dao funcionDao;

        private UpdateFuncionAsyncTask(funcion_dao funcionDao) {
            this.funcionDao = funcionDao;
        }

        @Override
        protected Void doInBackground(Funcion... funciones) {
            funcionDao.updateFuncion(funciones[0]);
            return null;
        }
    }

    private static class DeleteFuncionAsyncTask extends AsyncTask<Funcion, Void, Void> {
        private final funcion_dao funcionDao;

        private DeleteFuncionAsyncTask(funcion_dao funcionDao) {
            this.funcionDao = funcionDao;
        }

        @Override
        protected Void doInBackground(Funcion... funciones) {
            funcionDao.deleteFuncion(funciones[0]);
            return null;
        }
    }
}
