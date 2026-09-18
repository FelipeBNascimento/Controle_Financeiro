package com.financas.controle.exceptions;

public class CategoriaNaoEncontrada extends RuntimeException {


    public CategoriaNaoEncontrada(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoriaNaoEncontrada(String message) {
        super(message);



    }
}
