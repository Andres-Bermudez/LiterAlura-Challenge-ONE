package com.challengeone.literalura.clienteshttp;

import org.springframework.stereotype.Component;

@Component  // Esto hace que esta clase sea un bean gestionado por Spring
public class SolicitudesGutendexAPI {

    private static ClienteHttpGutendexAPI clienteHttpGutendexAPI = new ClienteHttpGutendexAPI();

    public static String solicitudAPI(String endpoint) {
        String json = null;
        try {
            json = clienteHttpGutendexAPI.solicitarGutendexAPI(endpoint);
            if (json != null) {
                return json;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
            return null;  // Solo retornamos null aquí
        }
        return null;
    }
}
