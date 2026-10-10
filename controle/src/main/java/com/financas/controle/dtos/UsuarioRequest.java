package com.financas.controle.dtos;

import java.math.BigDecimal;

public record UsuarioRequest(

        String nome,
        String email,
        String senha,
        BigDecimal saldo

) {
}
