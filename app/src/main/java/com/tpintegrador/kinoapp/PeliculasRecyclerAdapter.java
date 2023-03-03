package com.tpintegrador.kinoapp;

import android.view.LayoutInflater;
import android.view.View;
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
    private ListaPeliculasFragment.OnPeliculaClickListener adapterListener;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder{
        private PeliculaItemBinding mBinding;
        private TextView titulo;
        private TextView anio;
        private Button foro;
        private Button reserva;
        private ImageView imagen;
        private final ListaPeliculasFragment.OnPeliculaClickListener holderListener;
        public PeliculaViewHolder(PeliculaItemBinding binding, ListaPeliculasFragment.OnPeliculaClickListener mListener) {
            super(binding.getRoot());
            this.mBinding = binding;
            titulo = mBinding.Titulo;
            anio = mBinding.anio;
            foro = mBinding.botonForo;
            reserva = mBinding.botonReserva;
            imagen = mBinding.IconoPelicula;
            holderListener = mListener;
        }
        public void bind(Pelicula pelicula){
            titulo.setText(pelicula.getNombre());
            anio.setText(pelicula.getAño());
            imagen.setImageResource(pelicula.getImagen());
            foro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && holderListener != null) {
                        holderListener.OnPeliculaClick(pelicula);
                    }
                }
            });
        }
    }
    public PeliculasRecyclerAdapter(List<Pelicula> myDataset, ListaPeliculasFragment.OnPeliculaClickListener mListener){
        mDataset = myDataset;
        adapterListener = mListener;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PeliculaItemBinding binding = PeliculaItemBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false);
        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(binding, adapterListener);
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
