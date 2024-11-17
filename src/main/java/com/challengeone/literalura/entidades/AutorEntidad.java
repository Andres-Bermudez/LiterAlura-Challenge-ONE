package com.challengeone.literalura.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class AutorEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nombreAutor;

    @Column
    private Integer fechaNacimientoAutor;

    @Column
    private Integer fechaFallecimientoAutor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "libro_id")
    LibroEntidad libroEntidad;

    public AutorEntidad() {}

    public AutorEntidad(String nombreAutor, Integer fechaNacimientoAutor,
                        Integer fechaFallecimientoAutor) {
        this.nombreAutor = nombreAutor;
        this.fechaNacimientoAutor = fechaNacimientoAutor;
        this.fechaFallecimientoAutor = fechaFallecimientoAutor;
    }
}
