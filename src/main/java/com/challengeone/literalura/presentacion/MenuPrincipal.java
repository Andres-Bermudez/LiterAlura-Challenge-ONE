package com.challengeone.literalura.presentacion;

import com.challengeone.literalura.servicios.persistencia.BaseDatosServicio;
import com.challengeone.literalura.servicios.convertidores.BusquedaLibrosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component  // Hace que Spring gestione esta clase como un bean
public class MenuPrincipal {

    private final BaseDatosServicio baseDatosServicio;
    private final BusquedaLibrosServicio busquedaLibrosServicio;

    // Inyección de dependencias a través del constructor
    @Autowired
    public MenuPrincipal(BaseDatosServicio baseDatosServicio, BusquedaLibrosServicio busquedaLibrosServicio) {
        this.baseDatosServicio = baseDatosServicio;
        this.busquedaLibrosServicio = busquedaLibrosServicio;
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario;
        String menuPrincipal = """
                                \n ::::::::::::::: INICIO :::::::::::::::
                                     1. Buscar un libro.
                                     2. Listar libros almacenados.
                                     3. Listar autores registrados.
                                     4. Listar autores vivos en un determinado año.
                                     5. Listar libros por idioma.
                                     0. Salir
                               """;

        while (true) {
            System.out.print(menuPrincipal + "\nTu eleccion: ");
            try {
                eleccionUsuario = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nError: Ingresaste un caracter no valido.");
                sc.nextLine();  // Limpiar el buffer
                continue;
            }

            switch (eleccionUsuario) {
                case 0:
                    System.out.println("\nSaliendo de la aplicacion.....");
                    System.exit(0);
                    break;
                case 1:
                    busquedaLibrosServicio.buscarLibroPorTitulo();
                    break;
                case 2:
                    baseDatosServicio.listarLibrosAlmacenados();
                    break;
                case 3:
                    baseDatosServicio.listarAutoresRegistrados();
                    break;
                case 4:
                    busquedaLibrosServicio.listarAutoresVivosPorAno();
                    break;
                case 5:
                    busquedaLibrosServicio.listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("\nError: Esta opcion no esta disponible.");
                    System.out.println("Recuerda elegir unicamente entre las opciones disponibles " +
                            "en el menu principal");
                    break;
            }
        }
    }
}
