package com.caleta.lote.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.lote.dto.CreateLoteRequest;
import com.caleta.lote.dto.UpdateLoteRequest;
import com.caleta.lote.exception.ResourceNotFoundException;
import com.caleta.lote.model.Lote;
import com.caleta.lote.services.LoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lotes")
@Tag(name = "Lotes", description = "API para gestionar lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @Operation(
            summary = "Listar lotes",
            description = "Obtiene todos los lotes registrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Lote>> listar() {
        return ResponseEntity.ok(loteService.getLotes());
    }

    @Operation(
            summary = "Buscar lote por ID",
            description = "Obtiene un lote mediante su ID"
    )
    @ApiResponse(responseCode = "200", description = "Lote encontrado")
    @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Lote> obtener(@PathVariable Long id) {

        Lote lote = loteService.getLoteById(id);

        if (lote == null) {
            throw new ResourceNotFoundException("Lote no encontrado con id: " + id);
        }

        return ResponseEntity.ok(lote);
    }

    @Operation(
            summary = "Buscar lotes por captura",
            description = "Obtiene todos los lotes asociados a una captura"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping("/captura/{id}")
    public ResponseEntity<List<Lote>> obtenerPorCaptura(@PathVariable Long id) {
        return ResponseEntity.ok(loteService.getLotesByCaptura(id));
    }

    @Operation(
            summary = "Crear lote",
            description = "Registra un nuevo lote"
    )
    @ApiResponse(responseCode = "201", description = "Lote creado correctamente")
    @PostMapping
    public ResponseEntity<Lote> crearLote(

            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para registrar un lote",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación",
                                    summary = "Crear lote",
                                    value = """
                                    {
                                      "capturaId": 1,
                                      "precioBase": 2500.0
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody CreateLoteRequest request) {

        Lote nuevoLote = loteService.saveLote(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
    }

    @Operation(
            summary = "Actualizar lote",
            description = "Actualiza la información de un lote"
    )
    @ApiResponse(responseCode = "200", description = "Lote actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Lote> actualizarLote(

            @PathVariable Long id,

            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para actualizar un lote",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización",
                                    summary = "Actualizar lote",
                                    value = """
                                    {
                                      "capturaId": 2,
                                      "precioBase": 3200.0,
                                      "estado": "VENDIDO"
                                    }
                                    """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody UpdateLoteRequest request) {

        Lote loteActualizado = loteService.updateLote(id, request);

        if (loteActualizado == null) {
            throw new ResourceNotFoundException("Lote no encontrado con id: " + id);
        }

        return ResponseEntity.ok(loteActualizado);
    }

    @Operation(
            summary = "Eliminar lote",
            description = "Elimina un lote por su ID"
    )
    @ApiResponse(responseCode = "204", description = "Lote eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Lote no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable Long id) {

        loteService.deleteLote(id);

        return ResponseEntity.noContent().build();
    }

}