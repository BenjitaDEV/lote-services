package com.caleta.lote.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateLoteRequest(
    @NotBlank (message = "El ID de la captura es obligatorio")
    Long capturaId,
    @NotBlank (message = "El precio base es obligatorio")
    Double precioBase,
    @NotBlank (message = "El estado es obligatorio")
    String estado
) {

}
