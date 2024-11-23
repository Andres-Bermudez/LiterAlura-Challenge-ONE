package com.challengeone.literalura.servicios.persistencia;

import com.challengeone.literalura.modelos.dto.LibroDTO;
import com.challengeone.literalura.modelos.entidades.AutorEntidad;
import com.challengeone.literalura.modelos.entidades.LibroEntidad;
import com.challengeone.literalura.repositorios.AutorRepositorio;
import com.challengeone.literalura.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BaseDatosServicio {

    private final LibroRepository libroRepository;
    private final AutorRepositorio autorRepositorio;

    @Autowired
    public BaseDatosServicio(LibroRepository libroRepository, AutorRepositorio autorRepositorio) {
        this.libroRepository = libroRepository;
        this.autorRepositorio = autorRepositorio;
    }

    public void agregarLibro(LibroEntidad libroEntidad) {
        try {
            libroRepository.save(libroEntidad);
            System.out.println("\nLibro guardado en la base de datos.");
        } catch (DataIntegrityViolationException e) {
            System.out.println("\nEste libro ya esta almacenado en la base de datos.");
        }
    }

    @Transactional(readOnly = true)
    public void listarLibrosAlmacenados() {
        List<LibroEntidad> libros = libroRepository.findAll();

        libros.forEach(libro -> {
            // Construir la cadena de autores
            StringBuilder autores = new StringBuilder();
            libro.getAutores().forEach(autor -> autores.append(autor.toString()).append(", "));

            // Construir la cadena de idiomas
            StringBuilder idiomas = new StringBuilder();
            libro.getIdiomas().forEach(idioma -> idiomas.append(idioma.toString()).append(", "));

            // Construir la cadena de URLs
            StringBuilder urls = new StringBuilder();
            libro.getUrlsLibro().forEach((clave, valor) -> urls.append(clave).append(": ").append(valor).append("\n     "));

            // Imprimir la información del libro
            System.out.println(
                    "\nId: " + libro.getId() +
                    "\nTitulo: " + libro.getTitulo() +
                    "\nAutores: " + (!autores.isEmpty() ? autores.substring(0, autores.length() - 2) : "N/A") + // Eliminar la última coma y espacio
                    "\nIdiomas: " + (!idiomas.isEmpty() ? idiomas.substring(0, idiomas.length() - 2) : "N/A") + // Eliminar la última coma y espacio
                    "\nURLs: " + "\n    " + (!urls.isEmpty() ? urls.substring(0, urls.length() - 2) : "N/A") + // Eliminar la última coma y espacio
                    "\nTotal descargas: " + libro.getTotalDescargas()
            );
        });
    }

    public void listarAutoresRegistrados() {
        List<AutorEntidad> autores = autorRepositorio.findAll();
        autores.forEach(autor -> {
            System.out.println(
                    "\nId:  " + autor.getId() +
                    "\nNombre:  " + autor.getNombreAutor() +
                    "\nFecha de nacimiento: " + autor.getFechaNacimientoAutor() +
                    "\nFecha de fallecimiento: " + autor.getFechaFallecimientoAutor());
        });
    }
}
