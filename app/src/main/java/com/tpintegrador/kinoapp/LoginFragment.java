package com.tpintegrador.kinoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
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

public class LoginFragment extends Fragment {
    private LoginBinding logginBinding;
    private FirebaseAuth mAuth;
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
        logginBinding.ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar(usuario, contraseña);
            }
        });
        logginBinding.crearUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario(usuario, contraseña);
            }
        });

    }

    private void ingresar(TextInputLayout usuario, TextInputLayout contraseña) {
        if(usuario.getEditText().getText().toString().isEmpty() ||
                contraseña.getEditText().getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Complete los datos", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(usuario.getEditText().getText().toString(),
                    contraseña.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        showNewFragment();
                    }
                    else {
                        showAlert();
                    }
                }
            });
        }
    }

    private void crearUsuario(TextInputLayout usuario, TextInputLayout contraseña) {
        if(usuario.getEditText().getText().toString().isEmpty() ||
                contraseña.getEditText().getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Complete los datos", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.createUserWithEmailAndPassword(usuario.getEditText().getText().toString(),
                    contraseña.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(), "Usuario y contraseña creados", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        showAlert();
                    }
                }
            });
        }
    }

    private void showAlert() {
        Toast.makeText(getContext(), "No existe usuario", Toast.LENGTH_SHORT).show();
    }
    private void showNewFragment(){
        ListaPeliculasFragment listaPeliculasFragment = new ListaPeliculasFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listaPeliculasFragment);
        transaction.commit();
    }
}