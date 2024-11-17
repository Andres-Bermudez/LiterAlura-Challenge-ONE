package com.challengeone.literalura.modelos;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class Libro{
    private Integer id = 0;

    @SerializedName("title")
    private String titulo;

    @SerializedName("authors")
    private List<Autor> autores;

    @SerializedName("languages")
    List<String> idiomas;

    @SerializedName("formats")
    private Map<String, String> urlsLibro;

    @SerializedName("download_count")
    private Integer totalDescargas;

    public Libro() {
        id += 1;
    }

    @Override
    public String toString() {
        return "\n\nLibro:" +
                "\nTitulo: " + titulo +
                "\nAutores: " + autores +
                "\nURLs: " + urlsLibro +
                "\nTotal de descargas: " + totalDescargas;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Map<String, String> getUrlsLibro() {
        return urlsLibro;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }
}
