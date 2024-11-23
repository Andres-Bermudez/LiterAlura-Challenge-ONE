package com.challengeone.literalura.modelos.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record RespuestaApiDTO(

        @SerializedName("count")
        String totalLibrosEncontrados,

        @SerializedName("results")
        List<LibroDTO> resultadosEncontrados
) {
    @Override
    public String toString() {
        return "\nRespuesta de Gutendex: " +
               "\nTotal de libros encontrados: " + totalLibrosEncontrados +
               "\nTitulos que coinciden con tu busqueda: " + resultadosEncontrados;
    }
}
