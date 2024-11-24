package com.challengeone.literalura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.challengeone.literalura.modelos.entidades.LibroEntidad;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntidad, Long> {
    // Consultas personalizadas.

    @Query(value = "SELECT * FROM libros JOIN libro_idiomas " +
            "ON libros.id = libro_idiomas.libro_id WHERE libro_idiomas.idioma = :idiomaBuscado;",
            nativeQuery = true)
    List<LibroEntidad> librosPorIdioma(String idiomaBuscado);
}

