package com.challengeone.literalura.entidades;

import com.challengeone.literalura.modelos.Libro;
import jakarta.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class LibroEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String titulo;

    // Relación ManyToOne con la entidad AutorEntidad
    @OneToMany(mappedBy = "libroEntidad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AutorEntidad> autores;

    @ElementCollection(fetch = FetchType.EAGER)  // Usamos ElementCollection para colecciones simples
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    // Relación OneToMany con el Map de URLs (clave-valor)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_urls", joinColumns = @JoinColumn(name = "libro_id"))
    @MapKeyColumn(name = "clave")
    @Column(name = "url")
    private Map<String, String> urlsLibro;

    @Column
    private Integer totalDescargas;

    public LibroEntidad() {}

    public LibroEntidad(Libro libro) {
        this.titulo = libro.getTitulo();
        this.autores = libro.getAutores().stream()
                .map(a -> new AutorEntidad(a.nombreAutor(), a.fechaNacimientoAutor(), a.fechaFallecimientoAutor()))
                .collect(Collectors.toList());
        this.idiomas = libro.getIdiomas();
        this.urlsLibro = libro.getUrlsLibro();
        this.totalDescargas = libro.getTotalDescargas();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }
}

