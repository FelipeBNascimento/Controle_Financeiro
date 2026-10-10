package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.FormaPagamento;

import java.math.BigDecimal;

public record GastosRequest(
        String descricao,
        BigDecimal valor,
        Categoria categoria,
        //String motivo,
        FormaPagamento pagamento

) {
}
