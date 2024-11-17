package com.challengeone.literalura.repositorio;

import com.challengeone.literalura.entidades.LibroEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntidad, Integer> {

    @Query(value = "SELECT * FROM libros", nativeQuery = true)
    List<LibroEntidad> obtenerTodosLosLibrosAlmacenados();

    @Query(value = "SELECT * FROM autores", nativeQuery = true)
    List<LibroEntidad> obtenerTodosLosAutoresAlmacenados();
}
