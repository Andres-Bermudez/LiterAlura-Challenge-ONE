package com.challengeone.literalura.clienteshttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// La API Gutendex es un catálogo de información de más de 70.000 libros presentes en Project
// Gutenberg (biblioteca en línea y gratuita).
public class ClienteHttpGutendexAPI {
    private final String URL_BASE = "https://gutendex.com/books";

    // El método conexionAPI realiza una solicitud HTTP a una URL dada, sigue redirecciones
    // automáticas, y devuelve la respuesta como una cadena de texto si la solicitud es
    // exitosa (código de estado 200). En caso de un código de estado diferente o una
    // excepción, retorna un mensaje de error apropiado.
    public String solicitarGutendexAPI(String endpoint) {

        // Se crea una instancia de HttpClient utilizando un constructor (newBuilder()).
        HttpClient client = HttpClient.newBuilder()

                // Configura el cliente para que siga automáticamente redirecciones HTTP 301
                // (movido permanentemente) y 302 (encontrado) usando
                // followRedirects(HttpClient.Redirect.NORMAL).
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build(); // Finalmente, construye el HttpClient con .build().

        // Se crea una instancia de HttpRequest utilizando un constructor (newBuilder()).
        // Finalmente, construye el HttpRequest con .build().
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_BASE + endpoint)).build(); // URI: Configura la URI de la solicitud HTTP usando .uri(URI.create(url)), donde url es el parámetro pasado al método.

        // Se utiliza un bloque try-catch para manejar posibles excepciones.
        try {
            // Envía la solicitud HTTP.
            // HttpResponse.BodyHandlers.ofString(): Indica que el cuerpo de la respuesta debe ser
            // manejado como una cadena de texto.
            // HttpResponse: Almacena la respuesta en un objeto HttpResponse<String>.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Si el código de estado es 200 (OK), retorna el cuerpo de la respuesta con response.body().
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                // Si el código de estado no es 200, retorna un mensaje de error que incluye el código de estado.
                return "\nError: Código de estado: " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();  // Log de la excepción para facilitar el rastreo
            return "\n¡Error en la solicitud a la API! " + e.getMessage();
        }
    }
}
