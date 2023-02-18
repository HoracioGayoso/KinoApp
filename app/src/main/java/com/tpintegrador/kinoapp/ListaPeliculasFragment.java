package com.tpintegrador.kinoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.tpintegrador.kinoapp.databinding.ListaPelisBinding;
import com.tpintegrador.kinoapp.model.Pelicula;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaPeliculasFragment extends Fragment {
    private ListaPelisBinding mBinding;

    List<Pelicula> peliculas = new ArrayList<Pelicula>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = ListaPelisBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.lista_pelis, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView cabecera = view.findViewById(R.id.Cabecera);
        ImageView logout = view.findViewById(R.id.IconoLogOut);
        logout.setImageResource(R.drawable.ic_baseline_logout_24);
        ImageView perfil = view.findViewById(R.id.IconoPerfil);
        perfil.setImageResource(R.drawable.ic_baseline_person_24);
        cabecera.setText("Peliculas");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
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
        PeliculasRecyclerAdapter mAdapter = new PeliculasRecyclerAdapter(peliculas);
        RecyclerView peliculasRecyclerView = mBinding.PeliculaRecycler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        peliculasRecyclerView.setLayoutManager(layoutManager);
        peliculasRecyclerView.setAdapter(mAdapter);

    }
}