package com.tpintegrador.kinoapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.tpintegrador.kinoapp.databinding.ForumBinding;
import com.tpintegrador.kinoapp.model.Categorias_foro;
import com.tpintegrador.kinoapp.model.Foro;
import com.tpintegrador.kinoapp.model.Pelicula;
import com.tpintegrador.kinoapp.model.Publicacion_foro;
import com.tpintegrador.kinoapp.repositorios.foro_repositorio;
import com.tpintegrador.kinoapp.repositorios.publicacion_repositorio;

import java.util.List;


public class ForoFragment extends Fragment {
    static String channelId = "10";
    private ForumBinding foroBinding;
    private foro_repositorio repoForo;
    private Pelicula pelicula;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        foroBinding = ForumBinding.inflate(inflater, container, false);

//        return super.onCreateView(inflater, container, savedInstanceState);
        crearCanalNotificaciones();

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
        mAuth = FirebaseAuth.getInstance();

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
        Button botonVolver = foroBinding.volverCartelera;
        BroadcastReceiver br = new receiverNotificaciones();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_ALL_APPS);
        this.requireContext().registerReceiver(br,filter);




        final Integer[] idForo = {null};
        Categorias_foro categorias_foro = Categorias_foro.get(mSpinner.getSelectedItem().toString());

        botonAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                layoutPublicacion.setVisibility(View.GONE);
                layoutSecundario.setVisibility(View.VISIBLE);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                layoutPublicacion.setVisibility(View.VISIBLE);
                layoutSecundario.setVisibility(View.GONE);
                TextInputEditText campoTexto = foroBinding.comentarioEditText;
                campoTexto.getText().clear();
                int posicion = mSpinner.getSelectedItemPosition();
                mSpinner.setSelection(posicion);
            }
        });
        botonPublicar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                LinearLayout layoutPublicacion = foroBinding.layoutLista;
                LinearLayout layoutSecundario = foroBinding.layoutSecundario;
                TextInputEditText campoTexto = foroBinding.comentarioEditText;

                publicacion_repositorio repoPublicaciones = new publicacion_repositorio(getActivity().getApplication(), idForo[0]);
                repoPublicaciones.insert(new Publicacion_foro(idForo[0],mAuth.getCurrentUser().getEmail(),campoTexto.getText().toString()));
                layoutPublicacion.setVisibility(View.VISIBLE);
                layoutSecundario.setVisibility(View.GONE);
                campoTexto.getText().clear();
                Intent notificationIntent = new Intent(v.getContext(),receiverNotificaciones.class);
                notificationIntent.putExtra("nombreForo",mSpinner.getSelectedItem().toString().toLowerCase());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(v.getContext(),0,notificationIntent,PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,2000,pendingIntent);
                int posicion = mSpinner.getSelectedItemPosition();
                mSpinner.setSelection(posicion);

            }
        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAListaPeliculas();
            }
        });

        publicacion_repositorio repoPublicaciones = new publicacion_repositorio(getActivity().getApplication(),1);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                repoForo.getForoByPeliculaYCategoria(pelicula.getId(),getCategoriaFromSpinner(mSpinner)).observe(getViewLifecycleOwner(), new Observer<List<Foro>>() {
                    @Override
                    public void onChanged(List<Foro> foros) {

                        if(foros.size()!=0){
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
                        else{
                            Foro foroResenia = new Foro(pelicula.getId(),Categorias_foro.RESEÑAS);
                            Foro foroTeoria = new Foro(pelicula.getId(),Categorias_foro.TEORIAS);
                            Foro foroCuriosidades = new Foro(pelicula.getId(),Categorias_foro.CURIOSIDADES);
                            Foro foroAspectos = new Foro(pelicula.getId(),Categorias_foro.ASPECTOS_TECNICOS);

                            repoForo.insert(foroResenia);
                            repoForo.insert(foroTeoria);
                            repoForo.insert(foroCuriosidades);
                            repoForo.insert(foroAspectos);
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public Categorias_foro getCategoriaFromSpinner(Spinner spinner){
        return Categorias_foro.get(spinner.getSelectedItem().toString());
    }
    private void crearCanalNotificaciones() {
        CharSequence name = "CanalNotificacionesKino";
        String description = "Canal de notificaciones de KinoApp";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
    private void volverAListaPeliculas(){
        ListaPeliculasFragment listaPeliculasFragment = new ListaPeliculasFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,listaPeliculasFragment );
        transaction.commit();
    }
}