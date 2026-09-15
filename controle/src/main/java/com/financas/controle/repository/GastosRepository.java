package com.financas.controle.repository;

import com.financas.controle.model.GastosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<GastosEntity, Long> {

    List<GastosEntity> findAllByUsuarioId(Long id);

}
