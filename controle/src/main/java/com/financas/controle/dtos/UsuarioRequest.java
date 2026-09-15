package com.financas.controle.dtos;

public record UsuarioRequest(

        String nome,
        String email,
        String senha,
        Double saldo

) {
}
