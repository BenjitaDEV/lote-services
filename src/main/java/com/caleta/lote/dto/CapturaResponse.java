package com.caleta.lote.dto;

import java.time.LocalDate;

public class CapturaResponse {

    private Long id;
    private Long especieId;
    private Double kilos;
    private LocalDate fecha;

    public CapturaResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public Double getKilos() {
        return kilos;
    }

    public void setKilos(Double kilos) {
        this.kilos = kilos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }



}
