package com.tpintegrador.kinoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tpintegrador.kinoapp.databinding.ListaPelisBinding;
import com.tpintegrador.kinoapp.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ListaPeliculasActivity extends AppCompatActivity {
    List<Pelicula> peliculas = new ArrayList<Pelicula>();
    private ListaPelisBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        peliculas.add(new Pelicula(1, "Avengers", "2012", R.mipmap.avengers));
        peliculas.add(new Pelicula(2, "Avatar", "2009", R.mipmap.avatar_foreground));
        peliculas.add(new Pelicula(3, "Harry Potter", "2001", R.mipmap.harry_foreground));
        peliculas.add(new Pelicula(4, "Avengers", "2012", R.mipmap.avengers));
        peliculas.add(new Pelicula(5, "Avatar", "2009", R.mipmap.avatar_foreground));
        peliculas.add(new Pelicula(6, "Harry Potter", "2001", R.mipmap.harry_foreground));
        peliculas.add(new Pelicula(7, "Avengers", "2012", R.mipmap.avengers));
        peliculas.add(new Pelicula(8, "Avatar", "2009", R.mipmap.avatar_foreground));
        peliculas.add(new Pelicula(9, "Alicia en el pais de las maravillas", "2001", R.mipmap.harry_foreground));
        peliculas.add(new Pelicula(10, "Avengers", "2012", R.mipmap.avengers));
        peliculas.add(new Pelicula(11, "Avatar", "2009", R.mipmap.avatar_foreground));
        peliculas.add(new Pelicula(12, "Harry Potter", "2001", R.mipmap.harry_foreground));
        mBinding = ListaPelisBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        System.out.println(peliculas.size() + " peliculas");
        PeliculasRecyclerAdapter mAdapter = new PeliculasRecyclerAdapter(peliculas);
        RecyclerView peliculasRecyclerView = mBinding.PeliculaRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        peliculasRecyclerView.setLayoutManager(layoutManager);
        peliculasRecyclerView.setAdapter(mAdapter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView cabecera = findViewById(R.id.Cabecera);
        ImageView logout = findViewById(R.id.IconoLogOut);
        logout.setImageResource(R.drawable.ic_baseline_logout_24);
        ImageView profile = findViewById(R.id.IconoPerfil);
        profile.setImageResource(R.drawable.ic_baseline_person_24);
        cabecera.setText("Peliculas");
        setSupportActionBar(toolbar);
    }
}