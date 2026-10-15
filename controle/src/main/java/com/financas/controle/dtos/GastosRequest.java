package com.financas.controle.dtos;

import com.financas.controle.model.Categoria;
import com.financas.controle.model.FormaPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record GastosRequest(
        String descricao,

        @NotNull(message = "Valor obrigatório")
        @Positive
        BigDecimal valor,

        @NotNull
        Categoria categoria,
        //String motivo,
        @NotNull(message = "Método de pagamento obrigatório")
        FormaPagamento pagamento

) {
}
