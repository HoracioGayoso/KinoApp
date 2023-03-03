package com.tpintegrador.kinoapp.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Categorias_foro {
    RESEÑAS("Reseñas"),
    TEORIAS("Teorias"),
    CURIOSIDADES("Curiosidades"),
    ASPECTOS_TECNICOS("Aspectos Técnicos");

    private final String categoria;
    private static final Map<String,Categorias_foro> ENUM_MAP;

    Categorias_foro(String categoria){
        this.categoria = categoria;
    }
    public String getCategoria(){
        return categoria;
    }
    static {
        Map<String,Categorias_foro> map = new ConcurrentHashMap<String, Categorias_foro>();
        for (Categorias_foro instance : Categorias_foro.values()) {
            map.put(instance.getCategoria().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Categorias_foro get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
