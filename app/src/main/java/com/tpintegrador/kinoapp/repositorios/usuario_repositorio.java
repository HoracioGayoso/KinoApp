package com.tpintegrador.kinoapp.repositorios;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tpintegrador.kinoapp.AppDatabase;
import com.tpintegrador.kinoapp.dao.usuario_dao;
import com.tpintegrador.kinoapp.model.Usuario;

import java.util.List;

public class usuario_repositorio {
    private final usuario_dao usuarioDao;
    private LiveData<List<Usuario>> allUsuarios;

    public usuario_repositorio(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        usuarioDao = database.usuarioDao();
        allUsuarios = usuarioDao.getAllUsuarios();

    }

    public LiveData<List<Usuario>> getAllUsuarios() {
        return allUsuarios;
    }

    public LiveData<List<Usuario>> getForoByPeliculaYCategoria(String correo) {
        return usuarioDao.getUsuarioByCorreo(correo);
    }
    public void insert(Usuario usuario) {
        new usuario_repositorio.InsertUsuarioAsyncTask(usuarioDao).execute(usuario);
    }

    public void update(Usuario usuario) {
        new usuario_repositorio.UpdateUsuarioAsyncTask(usuarioDao).execute(usuario);
    }

    public void delete(Usuario usuario) {
        new usuario_repositorio.DeleteUsuarioAsyncTask(usuarioDao).execute(usuario);
    }

    private static class InsertUsuarioAsyncTask extends AsyncTask<Usuario, Void, Void> {
        private final usuario_dao usuarioDao;

        private InsertUsuarioAsyncTask(usuario_dao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.insertUsuario(usuarios[0]);
            return null;
        }
    }

    private static class UpdateUsuarioAsyncTask extends AsyncTask<Usuario, Void, Void> {
        private final usuario_dao usuarioDao;

        private UpdateUsuarioAsyncTask(usuario_dao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.updateUsuario(usuarios[0]);
            return null;
        }
    }

    private static class DeleteUsuarioAsyncTask extends AsyncTask<Usuario, Void, Void> {
        private final usuario_dao usuarioDao;

        private DeleteUsuarioAsyncTask(usuario_dao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.deleteUsuario(usuarios[0]);
            return null;
        }
    }
}
