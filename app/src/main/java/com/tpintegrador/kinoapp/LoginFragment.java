package com.tpintegrador.kinoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.tpintegrador.kinoapp.databinding.ListaPelisBinding;
import com.tpintegrador.kinoapp.databinding.LoginBinding;

public class LoginFragment extends Fragment {
    private LoginBinding logginBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logginBinding = LoginBinding.inflate(inflater, container, false);

        return logginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputLayout usuario = logginBinding.usuarioEditText;
        TextInputLayout contraseña = logginBinding.contraseAEditText;
        logginBinding.ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getEditText().getText().toString().equals("hola")){
                    if(contraseña.getEditText().getText().toString().equals("contraseña")){
                        Fragment listaPeliculasFragment = new ListaPeliculasFragment();
                        ((MainActivity)getActivity()).openFragment(listaPeliculasFragment);
                    }
                }else {
                    Toast.makeText(getContext(), "usuario o contraseñas incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}