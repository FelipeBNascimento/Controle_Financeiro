package com.financas.controle.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(IdNaoEncontrado.class)
    public ResponseEntity<MapeadorErros> idNaoEncontrado(IdNaoEncontrado e) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        MapeadorErros mapeadorErros = new MapeadorErros("Erro aconteceu porque não encontrou o ID", e.getMessage(), status.value());

        return ResponseEntity.status(status).body(mapeadorErros);
    }

    @ExceptionHandler(CategoriaNaoEncontrada.class)
    public ResponseEntity<MapeadorErros> categoriaNaoEncontrada(CategoriaNaoEncontrada e) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        MapeadorErros mapeadorErros = new MapeadorErros("Erro aconteceu porque categoria não existe", e.getMessage(), status.value());

        return ResponseEntity.status(status).body(mapeadorErros);
    }


}
