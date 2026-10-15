package com.financas.controle.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.ServerEndpoint;

import java.math.BigDecimal;

public record UsuarioRequest(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @Email(message = "Formato de emal inválido")
        @NotBlank(message = "email não pode ser nulo")
        String email,

        @NotBlank(message = "A sneha deve ser obrigatória")
        String senha,

        BigDecimal saldo

) {
}
