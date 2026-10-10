package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.FormaPagamento;
import com.financas.controle.model.UsuarioEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GastosResponse(

        String descricao,
        BigDecimal valor,
       // String motivo,
        LocalDate data,
        Categoria categoria,
        FormaPagamento pagamento
) {
}
