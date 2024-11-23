package com.challengeone.literalura.repositorios;

import com.challengeone.literalura.modelos.entidades.AutorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<AutorEntidad, Long> {
    // Consultas personalizadas.
}
