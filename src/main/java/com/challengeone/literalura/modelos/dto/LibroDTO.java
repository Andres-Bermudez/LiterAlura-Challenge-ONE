package com.challengeone.literalura.modelos.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class LibroDTO {

    private Long id = 0L;

    @SerializedName("title")
    private String titulo;

    @SerializedName("authors")
    private List<AutorDTO> autores;

    @SerializedName("languages")
    List<String> idiomas;

    @SerializedName("formats")
    private Map<String, String> urlsLibro;

    @SerializedName("download_count")
    private Long totalDescargas;

    public LibroDTO() {
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

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Map<String, String> getUrlsLibro() {
        return urlsLibro;
    }

    public Long getTotalDescargas() {
        return totalDescargas;
    }
}
