package com.tpintegrador.kinoapp.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tpintegrador.kinoapp.AppDatabase;
import com.tpintegrador.kinoapp.dao.foro_dao;

import com.tpintegrador.kinoapp.model.Categorias_foro;
import com.tpintegrador.kinoapp.model.Foro;

import java.util.List;

public class foro_repositorio {
    private final foro_dao foroDao;
    private LiveData<List<Foro>> allForos;

    public foro_repositorio(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        foroDao = database.foroDao();
        allForos = foroDao.getAllForos();

    }

    public LiveData<List<Foro>> getAllForos() {
        return allForos;
    }

    public LiveData<List<Foro>> getForoByPeliculaYCategoria(Integer pelicula, Categorias_foro categoria) {
        return foroDao.getForoByPeliculaYCategoria(pelicula, categoria);
    }
    public void insert(Foro foro) {
        new foro_repositorio.InsertForoAsyncTask(foroDao).execute(foro);
    }

    public void update(Foro foro) {
        new foro_repositorio.UpdateForoAsyncTask(foroDao).execute(foro);
    }

    public void delete(Foro foro) {
        new foro_repositorio.DeleteForoAsyncTask(foroDao).execute(foro);
    }

    private static class InsertForoAsyncTask extends AsyncTask<Foro, Void, Void> {
        private final foro_dao foroDao;

        private InsertForoAsyncTask(foro_dao foroDao) {
            this.foroDao = foroDao;
        }

        @Override
        protected Void doInBackground(Foro... foros) {
            foroDao.insertForo(foros[0]);
            return null;
        }
    }

    private static class UpdateForoAsyncTask extends AsyncTask<Foro, Void, Void> {
        private final foro_dao foroDao;

        private UpdateForoAsyncTask(foro_dao foroDao) {
            this.foroDao = foroDao;
        }

        @Override
        protected Void doInBackground(Foro... foros) {
            foroDao.updateForo(foros[0]);
            return null;
        }
    }

    private static class DeleteForoAsyncTask extends AsyncTask<Foro, Void, Void> {
        private final foro_dao foroDao;

        private DeleteForoAsyncTask(foro_dao foroDao) {
            this.foroDao = foroDao;
        }

        @Override
        protected Void doInBackground(Foro... foros) {
            foroDao.deleteForo(foros[0]);
            return null;
        }
    }
}
