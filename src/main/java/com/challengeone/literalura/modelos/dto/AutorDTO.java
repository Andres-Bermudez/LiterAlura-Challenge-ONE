package com.challengeone.literalura.modelos.dto;

import com.google.gson.annotations.SerializedName;

public record AutorDTO(

        @SerializedName("name")
        String nombreAutor,

        @SerializedName("birth_year")
        String fechaNacimientoAutor,

        @SerializedName("death_year")
        String fechaFallecimientoAutor
) {
    @Override
    public String toString() {
        return "    Nombre: " + nombreAutor() +
                "\n    Fecha de nacimiento: " + fechaNacimientoAutor() +
                "\n    Fecha de fallecimiento: " + fechaFallecimientoAutor();
    }
}
