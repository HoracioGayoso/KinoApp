package com.tpintegrador.kinoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.tpintegrador.kinoapp.databinding.ForumBinding;

import com.tpintegrador.kinoapp.model.Foro;
import com.tpintegrador.kinoapp.model.Pelicula;
import com.tpintegrador.kinoapp.repositorios.foro_repositorio;


public class ForoFragment extends Fragment {
    private ForumBinding foroBinding;
    private foro_repositorio repoForo;
    private Pelicula pelicula;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        foroBinding = ForumBinding.inflate(inflater, container, false);
//        repoForo = new foro_repositorio(getActivity().getApplication());
//        return super.onCreateView(inflater, container, savedInstanceState);
        foroBinding = ForumBinding.inflate(inflater, container, false);
        Spinner mSpinner = foroBinding.spinner;
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Categorias));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(myAdapter);
        Bundle args = getArguments();
        if (args != null) {
            pelicula = (Pelicula) args.getSerializable("pelicula");
        }
        foroBinding.textViewPelicula.setText(pelicula.getNombre());
        return foroBinding.getRoot();
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
        cabecera.setText("Foro");
//        Spinner mSpinner = foroBinding.spinner;
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(),
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Categorias));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(myAdapter);
    }
}