package com.caleta.lote.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Long id;

    @Column(name = "captura_id", nullable = false)
    private Long capturaId;

    @Column(name = "precio_base", nullable = false)
    private double precioBase;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Lote() {
    }

    public Lote(Long id,Long capturaId, double precioBase, String estado) {
        this.id = id;
        this.capturaId = capturaId;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCapturaId() {
        return capturaId;
    }

    public void setCapturaId(Long capturaId) {
        this.capturaId = capturaId;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

}
