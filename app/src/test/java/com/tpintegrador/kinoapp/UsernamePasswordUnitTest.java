package com.tpintegrador.kinoapp;

import static org.junit.Assert.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.DoNotMock;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tpintegrador.kinoapp.databinding.LoginBinding;
import com.tpintegrador.kinoapp.model.Usuario;
import com.tpintegrador.kinoapp.repositorios.usuario_repositorio;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;


public class UsernamePasswordUnitTest {


    MainActivity mainActivity;

    @Test
    public void test(){
        mainActivity = new MainActivity();
        LoginFragment login = new LoginFragment();
        mainActivity.openFragment(login);
        assertEquals(login,R.id.fragment_container);
    }

    @Test
    public void validacionUsuarioContaseña() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        usuario_repositorio repo = new usuario_repositorio(activity.getApplication());
//        LiveData<List<Usuario>> dataUsers = repo.getAllUsuarios();
//        List<Usuario> lista = dataUsers.getValue();
//        String nombreUsuario = null;
//        for(Usuario usuario: lista){
//            if(usuario.getCorreo().equals("horaciogayoso9@gmail.com")){
//                nombreUsuario = usuario.getNombre();
//            }
//        }
        mAuth.signInWithEmailAndPassword("horaciogayoso9@gmail.com",
                "Horacio09").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                }
                else {
                }
            }
        });
        assertEquals("horaciogayoso9@gmail.com",mAuth.getCurrentUser().getEmail());

    }
}
