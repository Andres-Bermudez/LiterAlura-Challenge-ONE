package com.challengeone.literalura.servicios;

import com.challengeone.literalura.api.gutendex.GutendexConsumoAPI;
import com.challengeone.literalura.consola.menus.MenuPrincipal;
import com.challengeone.literalura.entidades.LibroEntidad;
import com.challengeone.literalura.modelos.Libro;
import com.challengeone.literalura.modelos.RespuestaApi;
import com.challengeone.literalura.repositorio.LibroRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class SolicitudGutendexAPIServicio {
    private GutendexConsumoAPI gutendexConsumoAPI = new GutendexConsumoAPI();
    private List<Libro> listaLibrosEncontrados = new ArrayList<>();

    @Autowired
    private LibroRepository libroRepository;

    public void buscarLibroPorTitulo() {
        Scanner sc = new Scanner(System.in);
        String tituloLibroBuscado;

        while (true) {
            System.out.print("\nIngresa el titulo del libro que estas buscando: ");
            tituloLibroBuscado = sc.nextLine();

            System.out.println("\nBuscando el libro.....");
            // Formateo del titulo del libro para pasarlo a la url para hacer la solicitud GET a la API.
            String endpoint = "?search=" + tituloLibroBuscado.trim().replace(" ", "%20");
            String json;

            // Para tratar la excepcion en caso de que el usuario ingrese caracteres extraños.
            try {
                json = gutendexConsumoAPI.solicitarGutendexAPI(endpoint);
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
                continue;
            }

            // Deserializar la respuesta JSON en un objeto Java.
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            RespuestaApi respuestaApi;

            // Para tratar la excepcion en caso de que no sea posible parsear los datos.
            try {
                respuestaApi = gson.fromJson(json, RespuestaApi.class);
            } catch (JsonParseException e) {
                System.out.println("\nError: No se puedo convertir la respuesta JSON en un objeto Java.");
                System.out.println(e.getMessage());
                continue;
            }

            if (respuestaApi != null) {

                // Estadisticas de la cantidad de resultados encontrados
                System.out.println("\nSe han encontrado (" + respuestaApi.resultadosEncontrados().size() + ") resultados " +
                        "que coinciden con tu busqueda. \nSe ha seleccionado la primera coincidencia para ti.");

                // Imprimir los resultados encontrados
                listaLibrosEncontrados.addAll(respuestaApi.resultadosEncontrados());
                System.out.println(listaLibrosEncontrados.getFirst());

                if (libroRepository == null) {
                    System.out.println("\nEl repositorio no ha sido inyectado correctamente.");
                } else {
                    libroRepository.save(new LibroEntidad(listaLibrosEncontrados.getFirst()));
                    System.out.println("\nLibro guardado con exito.");
                }
                listaLibrosEncontrados.clear();
                break;
            }
        }
    }

    private void menuSecundario() {
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario;
        int indiceLibro = 0;

        while (true) {
            System.out.println("\nElige una opcion: ");
            System.out.println("    1. Guardar un libro.");
            System.out.println("    2. Volver al menu principal.");
            System.out.println("    0. Salir.");
            System.out.print("Tu eleccion: ");
            try {
                eleccionUsuario = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nError: Esta opcion no esta disponible.");
                continue;
            }

            switch (eleccionUsuario) {
                case 0:
                    System.out.println("\nSaliendo de la aplicacion");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("\nIngresa el No. del libro que quieres guardar: ");
                    try {
                        indiceLibro = sc.nextInt();
                        if (indiceLibro <= 0 ||indiceLibro > listaLibrosEncontrados.size()) {
                            System.out.println("\nEl No. de libro ingresado no existe.");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\nError: Ingresaste un caracter no valido.");
                    }
                    ConsultaBaseDatosServicio consultaBaseDatosServicio = new ConsultaBaseDatosServicio();
                    consultaBaseDatosServicio.agregarLibro(listaLibrosEncontrados.get(indiceLibro - 1));
                    break;
                case 2:
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.mostrarMenu();
                    break;
                default:
                    System.out.println("\nError: La opcion que ingresaste no esta disponible.");
                    break;
            }
            break;
        }
    }

    public void listarAutoresVivosPorAno() {
        Scanner sc = new Scanner(System.in);
        int anoBuscado;

        while (true) {
            System.out.print("\nIngresa el año del que quieres buscar autores: ");
            try {
                anoBuscado = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nError: El valor que ingresaste no es valido.");
                continue;
            }
            System.out.println("\nBuscando autores.....");
            String json = gutendexConsumoAPI.solicitarGutendexAPI("?author_year_start=" + anoBuscado);

            // Deserializar la respuesta JSON en un objeto Java.
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            RespuestaApi respuestaApi;

            // Para tratar la excepcion en caso de que no sea posible parsear los datos.
            try {
                respuestaApi = gson.fromJson(json, RespuestaApi.class);
            } catch (JsonParseException e) {
                System.out.println("\nError: No se puedo convertir la respuesta JSON en un objeto Java.");
                System.out.println(e.getMessage());
                continue;
            }
            listaLibrosEncontrados.addAll(respuestaApi.resultadosEncontrados());

            if (respuestaApi != null) {

                // Estadisticas de la cantidad de resultados encontrados
                System.out.println("\nSe han encontrado (" + respuestaApi.resultadosEncontrados().size() + ") resultados " +
                        "que coinciden con tu busqueda.");

                // Imprimir los resultados encontrados
                if (!listaLibrosEncontrados.isEmpty()) {
                    for (int i = 0; i < listaLibrosEncontrados.size(); i++) {
                        System.out.println("\n--------------------------------------------------------------------" +
                                "-------------------------------------------------");
                        System.out.println("No. " + (i + 1));
                        System.out.println("TITULO: " + listaLibrosEncontrados.get(i).getTitulo());

                        System.out.println("\nAUTORES: ");
                        listaLibrosEncontrados.get(i).getAutores().forEach(System.out::println);

//                        System.out.println("\nIDIOMAS: ");
//                        listaLibrosEncontrados.get(i).getIdiomas().forEach(idioma -> System.out.println("   >>> " + idioma));
//
//                        System.out.println("\nDOCUMENTACION:");
//                        listaLibrosEncontrados.get(i).getUrlsLibro().forEach((clave, valor) -> {
//                            System.out.println("    Dato: " + clave);
//                            System.out.println("    Informacion: " + valor + "\n");
//                        });
//                        System.out.println("Total de descargas: " + listaLibrosEncontrados.get(i).getTotalDescargas());
                    }
                }
            }
            listaLibrosEncontrados.clear();
            break;
        }
    }

    public void listarLibrosPorIdioma() {
        Scanner sc = new Scanner(System.in);
        String idiomaBuscado;

        while (true) {
            System.out.print("\nIngresa el idioma que quieres buscar: ");
            idiomaBuscado = sc.nextLine();

            System.out.println("\nBuscando libros por idioma.....");
            String json = gutendexConsumoAPI.solicitarGutendexAPI("?languages=" + idiomaBuscado);

            // Deserializar la respuesta JSON en un objeto Java.
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            RespuestaApi respuestaApi;

            // Para tratar la excepcion en caso de que no sea posible parsear los datos.
            try {
                respuestaApi = gson.fromJson(json, RespuestaApi.class);
            } catch (JsonParseException e) {
                System.out.println("\nError: No se puedo convertir la respuesta JSON en un objeto Java.");
                System.out.println(e.getMessage());
                continue;
            }
            listaLibrosEncontrados.addAll(respuestaApi.resultadosEncontrados());

            if (respuestaApi != null) {

                // Estadisticas de la cantidad de resultados encontrados
                System.out.println("\nSe han encontrado (" + respuestaApi.resultadosEncontrados().size() + ") resultados " +
                        "que coinciden con tu busqueda.");

                // Imprimir los resultados encontrados
                if (!listaLibrosEncontrados.isEmpty()) {
                    for (int i = 0; i < listaLibrosEncontrados.size(); i++) {
                        System.out.println("\n--------------------------------------------------------------------" +
                                "-------------------------------------------------");
                        System.out.println("No. " + (i + 1));
                        System.out.println("TITULO: " + listaLibrosEncontrados.get(i).getTitulo());

//                        System.out.println("\nAUTORES: ");
//                        listaLibrosEncontrados.get(i).getAutores().forEach(System.out::println);

                        System.out.println("\nIDIOMAS: ");
                        listaLibrosEncontrados.get(i).getIdiomas().forEach(idioma -> System.out.println("   >>> " + idioma));
//
//                        System.out.println("\nDOCUMENTACION:");
//                        listaLibrosEncontrados.get(i).getUrlsLibro().forEach((clave, valor) -> {
//                            System.out.println("    Dato: " + clave);
//                            System.out.println("    Informacion: " + valor + "\n");
//                        });
//                        System.out.println("Total de descargas: " + listaLibrosEncontrados.get(i).getTotalDescargas());
                    }
                }
                listaLibrosEncontrados.clear();
                break;
            }
        }
    }
}
