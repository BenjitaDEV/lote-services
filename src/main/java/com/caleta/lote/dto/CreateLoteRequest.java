package com.caleta.lote.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateLoteRequest (
    @NotBlank (message = "El ID de la captura es obligatorio")
    Long capturaId,
    @NotBlank (message = "El precio base es obligatorio")
    Double precioBase
) {

}
