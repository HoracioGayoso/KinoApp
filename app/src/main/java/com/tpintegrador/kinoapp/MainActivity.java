package com.tpintegrador.kinoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tpintegrador.kinoapp.databinding.MainActivityBinding;
import com.tpintegrador.kinoapp.databinding.ToolBarBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding maBinding;
    private ToolBarBinding tbBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maBinding = MainActivityBinding.inflate(getLayoutInflater());
        tbBinding = ToolBarBinding.inflate(getLayoutInflater());
        setContentView(maBinding.getRoot());
        tbBinding.Cabecera.setText("Peliculas");TextView cabecera = findViewById(R.id.Cabecera);
        cabecera.setText("Peliculas");
        tbBinding.IconoLogOut.setImageResource(R.drawable.ic_baseline_logout_24);
        tbBinding.IconoPerfil.setImageResource(R.drawable.ic_baseline_person_24);
        Toolbar toolbar = tbBinding.toolbar;
        setSupportActionBar(toolbar);
        LoginFragment loginFragment = new LoginFragment();
        openFragment(loginFragment);
    }
    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
