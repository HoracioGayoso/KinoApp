package com.tpintegrador.kinoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tpintegrador.kinoapp.databinding.ActivityMainBinding;
import com.tpintegrador.kinoapp.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Pelicula> peliculas = new ArrayList<Pelicula>();
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        peliculas.add(new Pelicula(1, "Avengers", "2012", 1));
        peliculas.add(new Pelicula(1, "Avatar", "2009", 2));
        peliculas.add(new Pelicula(1, "Harry Potter", "2001", 3));
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        System.out.println(peliculas.size() + " peliculas");
        PeliculasRecyclerAdapter mAdapter = new PeliculasRecyclerAdapter(peliculas);
        RecyclerView peliculasRecyclerView = mBinding.PeliculaRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        peliculasRecyclerView.setLayoutManager(layoutManager);
        peliculasRecyclerView.setAdapter(mAdapter);
    }
}