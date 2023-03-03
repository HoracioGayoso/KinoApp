package com.tpintegrador.kinoapp.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuario")
public class Usuario {
    @PrimaryKey
    @ColumnInfo(name = "correo")
    @NonNull
    String correo;
    @ColumnInfo(name = "nombre")
    String nombre;
    @ColumnInfo(name = "apellido")
    String apellido;

    public Usuario(){}

    public Usuario(String correo, String nombre, String apellido){
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
