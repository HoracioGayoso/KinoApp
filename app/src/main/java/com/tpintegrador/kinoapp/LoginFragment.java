package com.tpintegrador.kinoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tpintegrador.kinoapp.databinding.LoginBinding;
import com.tpintegrador.kinoapp.model.Usuario;
import com.tpintegrador.kinoapp.repositorios.usuario_repositorio;

public class LoginFragment extends Fragment {
    private LoginBinding logginBinding;
    private FirebaseAuth mAuth;
    private usuario_repositorio repoUsuario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logginBinding = LoginBinding.inflate(inflater, container, false);
        return logginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        TextInputLayout usuario = logginBinding.usuarioTextImputLayout;
        TextInputLayout contraseña = logginBinding.contrasenaTextImputLayout;
        repoUsuario = new usuario_repositorio(getActivity().getApplication());
        logginBinding.ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar(usuario, contraseña);
            }
        });
        logginBinding.crearUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout layoutUsuario = logginBinding.layoutSecundario;
                LinearLayout layoutPrincipal = logginBinding.layoutPrincipal;
                layoutPrincipal.setVisibility(Integer.parseInt("8"));
                layoutUsuario.setVisibility(Integer.parseInt("0"));
            }
        });
        logginBinding.confirmarNuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logginBinding.nuevoUsuario.getEditText().getText().toString().isEmpty() ||
                        logginBinding.nuevoUsuarioNombre.getEditText().getText().toString().isEmpty() ||
                        logginBinding.nuevoUsuarioApellido.getEditText().getText().toString().isEmpty() ||
                        logginBinding.nuevaContrasena.getEditText().getText().toString().isEmpty() ||
                        logginBinding.confirmarNuevaContrasena.getEditText().getText().toString().isEmpty()){
                    showAlert("Complete los datos");
                    return;
                }
                else if(!logginBinding.nuevaContrasena.getEditText().getText().toString().equals(logginBinding.confirmarNuevaContrasena.getEditText().getText().toString())){
                    showAlert("Contraseñas diferentes");
                    return;
                }
                LinearLayout layoutUsuario = logginBinding.layoutSecundario;
                LinearLayout layoutPrincipal = logginBinding.layoutPrincipal;
                layoutUsuario.setVisibility(Integer.parseInt("8"));
                layoutPrincipal.setVisibility(Integer.parseInt("0"));
                crearUsuario(logginBinding.nuevoUsuario, logginBinding.nuevaContrasena);
            }
        });
        logginBinding.cancelarNuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layoutUsuario = logginBinding.layoutSecundario;
                LinearLayout layoutPrincipal = logginBinding.layoutPrincipal;
                TextInputLayout usuario = logginBinding.nuevoUsuario;
                TextInputLayout contraseña = logginBinding.nuevaContrasena;
                usuario.getEditText().getText().clear();
                contraseña.getEditText().getText().clear();
                layoutPrincipal.setVisibility(Integer.parseInt("0"));
                layoutUsuario.setVisibility(Integer.parseInt("8"));
            }
        });
    }

    private void ingresar(TextInputLayout usuario, TextInputLayout contraseña) {
        if(usuario.getEditText().getText().toString().isEmpty() ||
                contraseña.getEditText().getText().toString().isEmpty()){
            showAlert("Complete los datos");
        } else {
            mAuth.signInWithEmailAndPassword(usuario.getEditText().getText().toString(),
                    contraseña.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        showNewFragment();
                    }
                    else {
                        showAlert("No existe usuario");
                    }
                }
            });
        }
    }


    private void crearUsuario(TextInputLayout usuario, TextInputLayout contraseña) {
        mAuth.createUserWithEmailAndPassword(usuario.getEditText().getText().toString(),
                contraseña.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    repoUsuario.insert(new Usuario(usuario.getEditText().getText().toString(),
                            logginBinding.nuevoUsuarioNombre.getEditText().getText().toString(),
                            logginBinding.nuevoUsuarioApellido.getEditText().getText().toString()));
                    showAlert("Usuario y contraseña creados");
                }
                else {
                    showAlert("No se pudo crear usuario");
                }
            }
        });
    }

    private void showAlert(String razon) {
        Toast.makeText(getContext(), razon, Toast.LENGTH_SHORT).show();
    }
    private void showNewFragment(){
        ListaPeliculasFragment listaPeliculasFragment = new ListaPeliculasFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listaPeliculasFragment);
        transaction.commit();
    }
}