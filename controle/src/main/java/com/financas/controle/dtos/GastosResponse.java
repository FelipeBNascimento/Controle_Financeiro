package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.UsuarioEntity;

import java.time.LocalDate;

public record GastosResponse(

        String descricao, Double valor, String motivo, LocalDate data, Categoria categoria
) {
}
