package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.FormaPagamento;

public record GastosRequest(
        String descricao,
        Double valor,
        Categoria categoria,
        //String motivo,
        FormaPagamento pagamento

) {
}
