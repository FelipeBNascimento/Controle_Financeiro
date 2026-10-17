package com.financas.controle.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.ServerEndpoint;

import java.math.BigDecimal;

public record UsuarioRequest(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Email(message = "Formato de email inválido")
        @NotBlank(message = "email não pode ser nulo")
        String email,

        @NotBlank(message = "A senha deve ser obrigatória")
        String senha,

        BigDecimal saldo

) {
}
