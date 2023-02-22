package com.tpintegrador.kinoapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tpintegrador.kinoapp.databinding.PeliculaItemBinding;
import com.tpintegrador.kinoapp.model.Pelicula;

import java.util.List;

public class PeliculasRecyclerAdapter extends RecyclerView.Adapter<PeliculasRecyclerAdapter
        .PeliculaViewHolder> {

    private List<Pelicula> mDataset;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder{
        private PeliculaItemBinding mBinding;
        TextView titulo;
        TextView anio;
        Button foro;
        Button reserva;
        ImageView imagen;

        public PeliculaViewHolder(PeliculaItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            titulo = mBinding.Titulo;
            anio = mBinding.anio;
            foro = mBinding.botonForo;
            reserva = mBinding.botonReserva;
            imagen = mBinding.IconoPelicula;
        }
        public void bind(Pelicula pelicula){
            titulo.setText(pelicula.getNombre());
            anio.setText(pelicula.getAño());
            imagen.setImageResource(pelicula.getImagen());
            //mBinding.IconoPelicula.setId(pelicula.getImagen());
         /*   foro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            reserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        }
    }
    public PeliculasRecyclerAdapter(List<Pelicula> myDataset){
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PeliculaItemBinding binding = PeliculaItemBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false);
        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(binding);
        return peliculaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = mDataset.get(position);
        holder.bind(pelicula);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
