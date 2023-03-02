package com.tpintegrador.kinoapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tpintegrador.kinoapp.databinding.PublicacionItemBinding;
import com.tpintegrador.kinoapp.model.Publicacion_foro;
import com.tpintegrador.kinoapp.model.Usuario;


import java.util.List;

public class PublicacionRecyclerAdapter extends RecyclerView.Adapter<PublicacionRecyclerAdapter
        .PublicacionViewHolder> {

    private List<Publicacion_foro> mDataset;
    private List<Usuario> usuariosList;

    public static class PublicacionViewHolder extends RecyclerView.ViewHolder{
        private PublicacionItemBinding itemBinding;
        TextView inicialNombre;
        TextView nombre;
        TextView comentario;
        public PublicacionViewHolder(PublicacionItemBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
            inicialNombre = itemBinding.letraInicial;
            nombre = itemBinding.nombreUsuario;
            comentario = itemBinding.post;
        }
        public void bind(Usuario usuario, Publicacion_foro publicacion){
            inicialNombre.setText(usuario.getNombre().substring(0,0));
            nombre.setText(usuario.getNombre().toString());
            comentario.setText(publicacion.getComentario());
        }
    }
    public PublicacionRecyclerAdapter(List<Publicacion_foro> myDataset, List<Usuario> usuariosList){
        mDataset = myDataset;
        usuariosList = usuariosList;
    }
    @NonNull
    @Override
    public PublicacionRecyclerAdapter.PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PublicacionItemBinding binding = PublicacionItemBinding.inflate(LayoutInflater.
                from(parent.getContext()), parent, false);
        PublicacionRecyclerAdapter.PublicacionViewHolder publicacionViewHolder = new PublicacionRecyclerAdapter.PublicacionViewHolder(binding);
        return publicacionViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        Publicacion_foro publicacion = mDataset.get(position);
        Usuario postOwner;
        for (Usuario usuario:
             usuariosList) {
            if(publicacion.getUsuario().equals(usuario.getCorreo())){
                postOwner = usuario;
                holder.bind(postOwner,publicacion);
            }
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
