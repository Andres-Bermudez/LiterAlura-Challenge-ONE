package com.challengeone.literalura.modelos.entidades;

import com.challengeone.literalura.modelos.dto.LibroDTO;
import jakarta.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class LibroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    // Relación ManyToOne con la entidad AutorEntidad
    @OneToMany(mappedBy = "libroEntidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AutorEntidad> autores;

    // Relación OneToMany con la colección de idiomas
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    // Relación OneToMany con el Map de URLs (clave-valor)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_urls", joinColumns = @JoinColumn(name = "libro_id"))
    @MapKeyColumn(name = "clave")
    @Column(name = "url")
    private Map<String, String> urlsLibro;

    @Column
    private Long totalDescargas;

    public LibroEntidad() {}

    // Constructor que recibe un DTO
    public LibroEntidad(LibroDTO libroDTO) {
        this.titulo = libroDTO.getTitulo();
        this.autores = libroDTO.getAutores().stream()
                .map(a -> new AutorEntidad(a.nombreAutor(), a.fechaNacimientoAutor(), a.fechaFallecimientoAutor(), this))
                .collect(Collectors.toList());
        this.idiomas = libroDTO.getIdiomas();
        this.urlsLibro = libroDTO.getUrlsLibro();
        this.totalDescargas = libroDTO.getTotalDescargas();
    }

    @Override
    public String toString() {
        return "\nId: " + id +
                "\nTitulo: " + titulo +
                "\nAutores: " + autores +
                "\nIdiomas: " + idiomas +
                "\nURLs: " + urlsLibro +
                "\nTotal descargas: " + totalDescargas;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorEntidad> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorEntidad> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Map<String, String> getUrlsLibro() {
        return urlsLibro;
    }

    public void setUrlsLibro(Map<String, String> urlsLibro) {
        this.urlsLibro = urlsLibro;
    }

    public Long getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Long totalDescargas) {
        this.totalDescargas = totalDescargas;
    }
}
