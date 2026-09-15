package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;

public record GastosRequest(
        String descricao,
        Double valor,
        Categoria categoria,
        String motivo
) {
}
