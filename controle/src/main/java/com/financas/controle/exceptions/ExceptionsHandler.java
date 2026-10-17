package com.financas.controle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

//    @ExceptionHandler(CategoriaNaoEncontrada.class)
//    public ResponseEntity<MapeadorErros> categoriaNaoEncontrada(CategoriaNaoEncontrada e) {
//
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//
//        MapeadorErros mapeadorErros = new MapeadorErros("Erro aconteceu porque categoria não existe", e.getMessage(), status.value());
//
//        return ResponseEntity.status(status).body(mapeadorErros);
//    }

    @ExceptionHandler(UsuarioNaoAutorizado.class)
    public ResponseEntity<MapeadorErros> usuarioNaoAutorizado(UsuarioNaoAutorizado e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;


        MapeadorErros mapeadorErros = new MapeadorErros("Usuario não Autenticado", e.getMessage(), status.value());

        return ResponseEntity.status(status).body(mapeadorErros);

    }


    // essa excessão valida as request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MapeadorErros> metodosEmBranco(MethodArgumentNotValidException e){

        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Pega a primeira mensagem de erro personalizada configurada no DTO (ex: "Valor obrigatório")
        String mensagemEspecifica = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // Ou se quiser saber qual foi o campo exato que deu erro (ex: "valor" ou "categoria")
         String campo = ((FieldError) e.getBindingResult().getAllErrors().get(0)).getField();

        MapeadorErros mapeadorErros = new MapeadorErros("Não pode ser nulo", campo + " está nulo " + mensagemEspecifica, status.value());

        return ResponseEntity.status(status).body(mapeadorErros);
    }


}
