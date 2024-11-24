package com.challengeone.literalura.repositorios;

import com.challengeone.literalura.modelos.entidades.AutorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutorRepositorio extends JpaRepository<AutorEntidad, Long> {
    // Consultas personalizadas.

    @Query(value = "SELECT * FROM autores WHERE fecha_fallecimiento_autor > :anoBuscado", nativeQuery = true)
    List<AutorEntidad> autoresPorAno(String anoBuscado);
}
