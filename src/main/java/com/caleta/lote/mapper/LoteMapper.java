package com.caleta.lote.mapper;

import com.caleta.lote.dto.*;
import com.caleta.lote.model.Lote;

public class LoteMapper {

    public static Lote toModel(CreateLoteRequest request){
        return new Lote(
            0L,
            request.capturaId(),
            request.precioBase(),
            "DISPONIBLE"
        );
    }

    public static Lote toModel(Long id, UpdateLoteRequest request){
        Lote lote = new Lote(
            id,
            request.capturaId(),
            request.precioBase(),
            request.estado()
        );
        return lote;
    }



}
