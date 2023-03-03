package com.tpintegrador.kinoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Publicaciones",
        foreignKeys = {@ForeignKey(entity = Foro.class,
                parentColumns = "id",
                childColumns = "foro_id"),
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "correo",
                        childColumns = "correo_usuario"
                )
            },
        indices = {
                @Index("foro_id"),
                @Index("correo_usuario"),
            }
        )
public class Publicacion_foro {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "foro_id")
    Integer Foro;
    @ColumnInfo(name = "correo_usuario")
    String usuario;
    @ColumnInfo(name = "comentario")
    String comentario;

    @Ignore
    public Publicacion_foro(){}

    public Publicacion_foro(Integer Foro, String usuario, String comentario){
        this.Foro = Foro;
        this.usuario = usuario;
        this.comentario = comentario;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForo() {
        return Foro;
    }

    public void setForo(Integer foro) {
        Foro = foro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
