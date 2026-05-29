package com.caleta.lote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateLoteRequest(
    @NotNull (message = "El ID de la captura es obligatorio") Long capturaId,
    @NotNull (message = "El precio base es obligatorio") Double precioBase,
    @NotBlank (message = "El estado es obligatorio") String estado
) {

}
