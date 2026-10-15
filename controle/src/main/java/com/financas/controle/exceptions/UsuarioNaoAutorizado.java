package com.financas.controle.exceptions;

public class UsuarioNaoAutorizado extends RuntimeException {
    public UsuarioNaoAutorizado(String message) {
        super(message);
    }
}
