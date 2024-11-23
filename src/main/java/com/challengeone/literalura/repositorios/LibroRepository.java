package com.challengeone.literalura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.challengeone.literalura.modelos.entidades.LibroEntidad;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntidad, Long> {
    // Consultas personalizadas.
}

