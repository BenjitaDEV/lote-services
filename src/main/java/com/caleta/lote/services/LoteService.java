package com.caleta.lote.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.caleta.lote.dto.CapturaResponse;
import com.caleta.lote.dto.CreateLoteRequest;
import com.caleta.lote.dto.UpdateLoteRequest;
import com.caleta.lote.model.Lote;
import com.caleta.lote.repository.LoteRepository;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final WebClient capturaWebClient;

    public LoteService(LoteRepository loteRepository, WebClient capturaWebClient) {
        this.loteRepository = loteRepository;
        this.capturaWebClient = capturaWebClient;
    }

    //GET
    public List<Lote> getLotes(){
        return loteRepository.findAll();
    }

    public Lote getLoteById(Long id){
        return loteRepository.findById(id).orElse(null);
    }

    public List<Lote> getLotesByCaptura(Long capturaId){
        return loteRepository.selectPorCapturaId(capturaId);
    }

    //REGLA DE NEGOCIO CREATE

    public Lote saveLote(CreateLoteRequest request){

        if(request.capturaId() == null){
            throw new RuntimeException("El lote debe tneer una captura asociada");
        }

        if (request.precioBase() <= 0){
            throw new RuntimeException("El precio base debe ser mayor a 0");
        }

        //VALIDAR CAPTURA (MICROSERVICIO CAPTURA)
        CapturaResponse captura = capturaWebClient.get()
            .uri("/{id}", request.capturaId())
            .retrieve()
            .bodyToMono(CapturaResponse.class)
            .block();
        
        if (captura == null){
            throw new RuntimeException("la captura no existe");
        }

        Lote lote = new Lote();
        
        lote.setCapturaId(request.capturaId());
        lote.setPrecioBase(request.precioBase());

        //estado automatico
        lote.setEstado("DISPONIBLE");

        return loteRepository.save(lote);
    }

    //UPDATE

    public Lote updateLote(Long id, UpdateLoteRequest request){

        Lote lote = loteRepository.findById(id).orElseThrow(() -> new RuntimeException("El lote no existe"));

        if (lote != null){

            if (request.precioBase() <= 0){
                throw new RuntimeException("El precio base debe ser mayor a 0");
            }

            lote.setPrecioBase(request.precioBase());
            lote.setEstado(request.estado());
            lote.setCapturaId(request.capturaId());

            return loteRepository.save(lote);

        }

        return null;
    }

    public void deleteLote(Long id){
        loteRepository.deleteById(id);
    }



}
