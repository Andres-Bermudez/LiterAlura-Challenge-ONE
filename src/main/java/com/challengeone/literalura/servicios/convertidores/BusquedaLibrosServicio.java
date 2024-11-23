package com.challengeone.literalura.servicios.convertidores;

import com.challengeone.literalura.clienteshttp.SolicitudesGutendexAPI;
import com.challengeone.literalura.modelos.dto.LibroDTO;
import com.challengeone.literalura.modelos.dto.RespuestaApiDTO;
import com.challengeone.literalura.modelos.entidades.LibroEntidad;
import com.challengeone.literalura.servicios.persistencia.BaseDatosServicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BusquedaLibrosServicio {

    private List<LibroDTO> listaLibrosEncontrados = new ArrayList<>();
    private final BaseDatosServicio baseDatosServicio;
    private final SolicitudesGutendexAPI solicitudesGutendexAPI; // Inyección de SolicitudesGutendexAPI

    // Inyección de dependencias
    @Autowired
    public BusquedaLibrosServicio(BaseDatosServicio baseDatosServicio, SolicitudesGutendexAPI solicitudesGutendexAPI) {
        this.baseDatosServicio = baseDatosServicio;
        this.solicitudesGutendexAPI = solicitudesGutendexAPI;  // Asignar a la propiedad
    }

    public void buscarLibroPorTitulo() {
        Scanner sc = new Scanner(System.in);
        String tituloLibroBuscado;

        while (true) {
            System.out.print("\nIngresa el titulo del libro que estas buscando: ");
            tituloLibroBuscado = sc.nextLine();

            System.out.println("\nBuscando el libro.....");
            // Formateo del titulo del libro para pasarlo a la url para hacer la solicitud GET a la API.
            String endpoint = "?search=" + tituloLibroBuscado.trim().replace(" ", "%20");

            // Hacer la solicitud a la API y recibir una respuesta.
            String respuestaApiJson = solicitudesGutendexAPI.solicitudAPI(endpoint);  // Ahora lo llamas de la instancia

            // Convertir los datos recibidos.
            if (respuestaApiJson != null) {
                // Deserializar la respuesta JSON en un objeto Java.
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                RespuestaApiDTO respuestaApiDTO;

                try {
                    respuestaApiDTO = gson.fromJson(respuestaApiJson, RespuestaApiDTO.class);
                } catch (JsonParseException e) {
                    System.out.println("\nError: No se pudo convertir la respuesta JSON en un objeto Java.");
                    System.out.println(e.getMessage());
                    continue;
                }

                if (respuestaApiDTO != null) {
                    // Estadísticas de la cantidad de resultados encontrados
                    System.out.println("\nSe han encontrado (" + respuestaApiDTO.resultadosEncontrados().size() + ") resultados " +
                            "que coinciden con tu búsqueda. \nSe ha seleccionado la primera coincidencia para ti.");

                    // Imprimir los resultados encontrados
                    listaLibrosEncontrados.addAll(respuestaApiDTO.resultadosEncontrados());
                    System.out.println(listaLibrosEncontrados.get(0));  // Usamos get(0) en lugar de getFirst()

                    // Almacenar el libro encontrado en la base de datos.
                    baseDatosServicio.agregarLibro(new LibroEntidad(listaLibrosEncontrados.get(0)));

                    // Reiniciar la lista de libros encontrados.
                    listaLibrosEncontrados.clear();
                    break;
                }
            } else {
                System.out.println("\nLa consulta a la API de Gutendex retornó como respuesta un NULL.");
            }
        }
    }

    public void listarAutoresVivosPorAno() {
    }

    public void listarLibrosPorIdioma() {
    }
}

