package com.financas.controle.exceptions;

public class IdNaoEncontrado extends RuntimeException {

    public IdNaoEncontrado(String message) {
        super(message);

    }

    public IdNaoEncontrado(String messagem, Throwable e){

        super(messagem, e.getCause());
    }
}
