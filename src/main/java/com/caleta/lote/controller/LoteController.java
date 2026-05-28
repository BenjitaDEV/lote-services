package com.caleta.lote.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caleta.lote.services.LoteService;
import com.caleta.lote.dto.*;
import com.caleta.lote.exception.*;
import com.caleta.lote.mapper.LoteMapper;
import com.caleta.lote.model.*;
import com.caleta.lote.repository.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService){
        this.loteService = loteService;
    }

    //Get all
    @GetMapping
    public ResponseEntity<List<Lote>> listar(){
        return ResponseEntity.ok(loteService.getLotes());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Lote> obtener(@PathVariable Long id){
        Lote lote = loteService.getLoteById(id);

        if(lote == null){
            throw new ResourceNotFoundException("Lote no encontrado con id: " + id);

        }

        return ResponseEntity.ok(lote);
    }

    //Get por captura
    @GetMapping("/captura/{id}")
    public ResponseEntity<List<Lote>> obtenerPorCaptura(@PathVariable Long id){
        return ResponseEntity.ok(loteService.getLotesByCaptura(id));
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Lote> crearLote(@RequestBody CreateLoteRequest request){
        Lote nuevoLote = loteService.saveLote(LoteMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Lote> actualizarLote(@PathVariable Long id, @Valid @RequestBody UpdateLoteRequest request){

        Lote loteActualizado = loteService.updateLote(id, LoteMapper.toModel(id, request));

        if(loteActualizado == null){
            throw new ResourceNotFoundException("Lote no encontrado con id: " + id);
        }

        return ResponseEntity.ok(loteActualizado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable Long id){
        loteService.deleteLote(id);
        return ResponseEntity.noContent().build();
    }

}
