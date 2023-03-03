package com.tpintegrador.kinoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tpintegrador.kinoapp.databinding.ForumBinding;
import com.tpintegrador.kinoapp.model.Categorias_foro;
import com.tpintegrador.kinoapp.model.Foro;
import com.tpintegrador.kinoapp.model.Pelicula;
import com.tpintegrador.kinoapp.model.Publicacion_foro;
import com.tpintegrador.kinoapp.repositorios.foro_repositorio;
import com.tpintegrador.kinoapp.repositorios.publicacion_repositorio;

import java.util.List;


public class ForoFragment extends Fragment {
    private ForumBinding foroBinding;
    private foro_repositorio repoForo;
    private Pelicula pelicula;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        foroBinding = ForumBinding.inflate(inflater, container, false);

//        return super.onCreateView(inflater, container, savedInstanceState);
        foroBinding = ForumBinding.inflate(inflater, container, false);
        Spinner mSpinner = foroBinding.spinner;
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Categorias));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(myAdapter);
        Button botonCartelera = foroBinding.volverCartelera;

        Bundle args = getArguments();
        if (args != null) {
            pelicula = (Pelicula) args.getSerializable("pelicula");
        }
        repoForo = new foro_repositorio(getActivity().getApplication());
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
        Spinner mSpinner = foroBinding.spinner;
        Button botonAniadir = foroBinding.aniadirComentario;
        Button botonCancelar = foroBinding.cancelarPublicarButton;
        Button botonPublicar = foroBinding.publicarButton;
        final Integer[] idForo = {null};
        Categorias_foro categorias_foro = Categorias_foro.get(mSpinner.getSelectedItem().toString());

        botonAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                System.out.println("Clickeo!");
                layoutPublicacion.setVisibility(View.GONE);
                layoutSecundario.setVisibility(View.VISIBLE);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                System.out.println("Clickeo!");
                layoutPublicacion.setVisibility(View.VISIBLE);
                layoutSecundario.setVisibility(View.GONE);
            }
        });
        botonPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                TextInputEditText campoTexto = foroBinding.comentarioEditText;

                publicacion_repositorio repoPublicaciones = new publicacion_repositorio(getActivity().getApplication(), idForo[0]);
                repoPublicaciones.insert(new Publicacion_foro(idForo[0],"escowichmartin@gmail.com",campoTexto.getText().toString()));
                layoutPublicacion.setVisibility(View.VISIBLE);
                layoutSecundario.setVisibility(View.GONE);
                campoTexto.setText("");
                Integer posicion = mSpinner.getSelectedItemPosition();
                mSpinner.setSelection(posicion);
            }
        });



        publicacion_repositorio repoPublicaciones = new publicacion_repositorio(getActivity().getApplication(),1);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                repoForo.getForoByPeliculaYCategoria(pelicula.getId(),getCategoriaFromSpinner(mSpinner)).observe(getViewLifecycleOwner(), new Observer<List<Foro>>() {
                    @Override
                    public void onChanged(List<Foro> foros) {

                        if(foros!=null){
                            System.out.println("Foros es "+foros.toString()+"\n");
                             idForo[0] = foros.get(0).getId();
                            publicacion_repositorio repoPublicaciones = new publicacion_repositorio(getActivity().getApplication(), idForo[0]);
                            repoPublicaciones.getPublicacionesByForo().observe(getViewLifecycleOwner(), new Observer<List<Publicacion_foro>>() {
                                @Override
                                public void onChanged(List<Publicacion_foro> publicacion_foros) {
                                    if(publicacion_foros!=null){
                                        System.out.println("Se entro al onchanged de adentro");
                                        PublicacionRecyclerAdapter mAdapter = new PublicacionRecyclerAdapter(publicacion_foros);
                                        RecyclerView publicacionRecyclerView = foroBinding.recyclerViewForum;
                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                        publicacionRecyclerView.setLayoutManager(layoutManager);
                                        publicacionRecyclerView.setAdapter(mAdapter);
                                    }
                                }
                            });


                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinner.setSelection(0);

    }
    public Categorias_foro getCategoriaFromSpinner(Spinner spinner){
        return Categorias_foro.get(spinner.getSelectedItem().toString());
    }
}