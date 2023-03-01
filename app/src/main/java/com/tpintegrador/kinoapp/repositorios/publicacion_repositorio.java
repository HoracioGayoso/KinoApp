package com.tpintegrador.kinoapp.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tpintegrador.kinoapp.AppDatabase;
import com.tpintegrador.kinoapp.dao.publicacion_dao;
import com.tpintegrador.kinoapp.model.Publicacion_foro;

import java.util.List;

public class publicacion_repositorio {
    private final publicacion_dao publicacionDao;
    private LiveData<List<Publicacion_foro>> publicacionesByForo;
    public publicacion_repositorio(Application application, Integer foro) {
        AppDatabase database = AppDatabase.getInstance(application);
        publicacionDao = database.publicaionDao();
        publicacionesByForo = publicacionDao.getPublicacionByForo(foro);
    }
    public LiveData<List<Publicacion_foro>> getPublicacionesByForo() {
        return publicacionesByForo;
    }
    public void insert(Publicacion_foro publicacion) {
        new publicacion_repositorio.InsertPublicacionAsyncTask(publicacionDao).execute(publicacion);
    }

    public void update(Publicacion_foro publicacion) {
        new publicacion_repositorio.UpdatePublicacionAsyncTask(publicacionDao).execute(publicacion);
    }

    public void delete(Publicacion_foro publicacion) {
        new publicacion_repositorio.DeletePublicacionAsyncTask(publicacionDao).execute(publicacion);
    }

    private static class InsertPublicacionAsyncTask extends AsyncTask<Publicacion_foro, Void, Void> {
        private final publicacion_dao publicacionDao;

        private InsertPublicacionAsyncTask(publicacion_dao publicacionDao) {
            this.publicacionDao = publicacionDao;
        }

        @Override
        protected Void doInBackground(Publicacion_foro... publicaciones) {
            publicacionDao.insertPublicacion(publicaciones[0]);
            return null;
        }
    }

    private static class UpdatePublicacionAsyncTask extends AsyncTask<Publicacion_foro, Void, Void> {
        private final publicacion_dao publicacionDao;

        private UpdatePublicacionAsyncTask(publicacion_dao publicacionDao) {
            this.publicacionDao = publicacionDao;
        }

        @Override
        protected Void doInBackground(Publicacion_foro... publicaciones) {
            publicacionDao.updatePublicacion(publicaciones[0]);
            return null;
        }
    }

    private static class DeletePublicacionAsyncTask extends AsyncTask<Publicacion_foro, Void, Void> {
        private final publicacion_dao publicacionDao;

        private DeletePublicacionAsyncTask(publicacion_dao publicacionDao) {
            this.publicacionDao = publicacionDao;
        }

        @Override
        protected Void doInBackground(Publicacion_foro... publicaciones) {
            publicacionDao.deletePublicacion(publicaciones[0]);
            return null;
        }
    }
}


