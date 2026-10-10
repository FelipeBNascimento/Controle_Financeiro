package com.financas.controle.repository;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.GastosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<GastosEntity, Long> {

    List<GastosEntity> findAllByUsuarioIdAndCategoria(Long id, Categoria categoria);


    List<GastosEntity> findAllByCategoria(Categoria categoria);

    @Query("SELECT SUM(g.valor) AS total_soma\n" +
            "FROM GastosEntity g where g.categoria = :categoria AND g.usuario.id = :id_usuario")
    BigDecimal somarGastosPorCategoria(@Param("categoria") Categoria categoria, @Param("id_usuario") Long id);

}
