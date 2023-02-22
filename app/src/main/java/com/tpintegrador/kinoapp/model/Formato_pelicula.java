package com.tpintegrador.kinoapp.model;

public enum Formato_pelicula {
    TRES_D_SUBTITULADA("3D Subtitulada"),
    DOS_D_SUBTITULADA("2D Subtitulada"),
    TRES_D_CASTELLANO("3D Castellano"),
    DOS_D_CASTELLANO("2D Castellano");

    private final String formato;
    Formato_pelicula(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }
}
