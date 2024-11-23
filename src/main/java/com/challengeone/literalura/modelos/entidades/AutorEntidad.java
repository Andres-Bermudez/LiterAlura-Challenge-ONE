package com.challengeone.literalura.modelos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class AutorEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreAutor;

    @Column
    private String fechaNacimientoAutor;

    @Column
    private String fechaFallecimientoAutor;

    // Relación ManyToOne con LibroEntidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private LibroEntidad libroEntidad;

    public AutorEntidad() {}

    public AutorEntidad(String nombreAutor, String fechaNacimientoAutor, String fechaFallecimientoAutor, LibroEntidad libroEntidad) {
        this.nombreAutor = nombreAutor;
        this.fechaNacimientoAutor = fechaNacimientoAutor;
        this.fechaFallecimientoAutor = fechaFallecimientoAutor;
        this.libroEntidad = libroEntidad;
    }

    @Override
    public String toString() {
        return "\n     Nombre: " + nombreAutor +
                "\n     Fecha de nacimiento: " + fechaNacimientoAutor +
                "\n     Fecha de fallecimiento: " + fechaFallecimientoAutor;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getFechaNacimientoAutor() {
        return fechaNacimientoAutor;
    }

    public void setFechaNacimientoAutor(String fechaNacimientoAutor) {
        this.fechaNacimientoAutor = fechaNacimientoAutor;
    }

    public String getFechaFallecimientoAutor() {
        return fechaFallecimientoAutor;
    }

    public void setFechaFallecimientoAutor(String fechaFallecimientoAutor) {
        this.fechaFallecimientoAutor = fechaFallecimientoAutor;
    }

    public LibroEntidad getLibroEntidad() {
        return libroEntidad;
    }

    public void setLibroEntidad(LibroEntidad libroEntidad) {
        this.libroEntidad = libroEntidad;
    }
}
