package com.challengeone.literalura.modelos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record RespuestaApi(
        @SerializedName("count")
        String totalLibrosEncontrados,

        @SerializedName("results")
        List<Libro> resultadosEncontrados
) {
    @Override
    public String toString() {
        return "\nRespuesta de Gutendex: " +
               "\nTotal de libros encontrados: " + totalLibrosEncontrados +
               "\nTitulos que coinciden con tu busqueda: " + resultadosEncontrados;
    }
}
