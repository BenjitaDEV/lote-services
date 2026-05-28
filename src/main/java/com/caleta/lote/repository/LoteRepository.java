package com.caleta.lote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caleta.lote.model.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long>{
    
    @Query(value = "SELECT * FROM lotes WHERE captura_id = :capturaId", nativeQuery = true)
    List<Lote> selectPorCapturaId(Long capturaId);

}
