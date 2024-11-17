package com.challengeone.literalura.consola.menus;

import com.challengeone.literalura.servicios.ConsultaBaseDatosServicio;
import com.challengeone.literalura.servicios.SolicitudGutendexAPIServicio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    public void mostrarMenu() {
        ConsultaBaseDatosServicio consultaBaseDatosServicio = new ConsultaBaseDatosServicio();
        SolicitudGutendexAPIServicio solicitudGutendexAPIServicio = new SolicitudGutendexAPIServicio();
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario;
        String menuPrincipal = """
                                \n ::::::::::::::: INICIO :::::::::::::::
                                     1. Buscar un libro por su titulo.
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
                sc.nextLine();
                continue;
            }

            switch (eleccionUsuario) {
                case 0:
                    System.out.println("\nSaliendo de la aplicacion.....");
                    System.exit(0);
                    break;
                case 1:
                    solicitudGutendexAPIServicio.buscarLibroPorTitulo();
                    break;
                case 2:
                    consultaBaseDatosServicio.listarLibrosAlmacenados();
                    break;
                case 3:
                    consultaBaseDatosServicio.listarAutoresRegistrados();
                    break;
                case 4:
                    solicitudGutendexAPIServicio.listarAutoresVivosPorAno();
                    break;
                case 5:
                    solicitudGutendexAPIServicio.listarLibrosPorIdioma();
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
