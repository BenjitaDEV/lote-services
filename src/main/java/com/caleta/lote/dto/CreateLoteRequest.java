package com.caleta.lote.dto;

import jakarta.validation.constraints.NotNull;

public record CreateLoteRequest (
    @NotNull (message = "El ID de la captura es obligatorio")
    Long capturaId,
    @NotNull (message = "El precio base es obligatorio")
    Double precioBase
) {

}
