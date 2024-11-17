package com.challengeone.literalura.servicios;

import com.challengeone.literalura.entidades.LibroEntidad;
import com.challengeone.literalura.modelos.Libro;
import com.challengeone.literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaBaseDatosServicio {

    @Autowired
    private LibroRepository libroRepository;

    public void listarLibrosAlmacenados() {
        System.out.println("\nConsultando libros almacenados.....");
        System.out.println(libroRepository.obtenerTodosLosLibrosAlmacenados());
    }

    public void listarAutoresRegistrados() {
        System.out.println("\nConsultando autores almacenados.....");
        System.out.println(libroRepository.obtenerTodosLosAutoresAlmacenados());
    }

    public void agregarLibro(Libro libro) {
        if (libroRepository == null) {
            System.out.println("\nEl repositorio no ha sido inyectado correctamente.");
        } else {
            LibroEntidad libroEntidad = new LibroEntidad(libro);
            libroRepository.save(libroEntidad);
            System.out.println("\nLibro guardado con exito.");
        }
    }
}
