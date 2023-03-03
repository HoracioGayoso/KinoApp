package com.tpintegrador.kinoapp;

import android.content.Context;
import android.content.res.Configuration;
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
import com.tpintegrador.kinoapp.repositorios.pelicula_repositorio;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaPeliculasFragment extends Fragment {
    private ListaPelisBinding mBinding;
    private pelicula_repositorio peliculaRepositorio;
    List<Pelicula> peliculas = new ArrayList<Pelicula>();
    private OnPeliculaClickListener mListener;


    public interface OnPeliculaClickListener {
        void OnPeliculaClick(Pelicula pelicula);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPeliculaClickListener) {
            mListener = (OnPeliculaClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieClickListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = ListaPelisBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.lista_pelis, container, false);
        peliculaRepositorio = new pelicula_repositorio(getActivity().getApplication());
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
//        peliculaRepositorio.insert(new Pelicula("Avengers", "2012", R.mipmap.avengers,
//                "Nick Fury, director de la agencia internacional S.H.I.E.L.D, necesita un equipo para evitar un desastre mundial"));
//        peliculaRepositorio.insert(new Pelicula("Avatar", "2009", R.mipmap.avatar_foreground,
//                "Un exmarine se une a los Na'vi en su lucha contra la explotación de los recursos de su planeta por parte de los humanos."));
//        peliculaRepositorio.insert(new Pelicula("Harry Potter", "2001", R.mipmap.harry_foreground,
//                "Harry, Ron y Hermione se unen para enfrentar una amenaza en Hogwarts y descubrir la verdad sobre los padres de Harry."));
        peliculaRepositorio.getAllPeliculas().observe(getViewLifecycleOwner(), new Observer<List<Pelicula>>() {
            @Override
            public void onChanged(List<Pelicula> peliculas) {
                // Actualizar la vista con la nueva lista de tareas
                PeliculasRecyclerAdapter mAdapter = new PeliculasRecyclerAdapter(peliculas, mListener);
                RecyclerView peliculasRecyclerView = mBinding.PeliculaRecycler;
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                peliculasRecyclerView.setLayoutManager(layoutManager);
                peliculasRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}