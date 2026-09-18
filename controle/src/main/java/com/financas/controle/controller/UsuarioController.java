package com.financas.controle.controller;

import com.financas.controle.dtos.UsuarioRequest;
import com.financas.controle.dtos.UsuarioResponse;
import com.financas.controle.exceptions.ExceptionsHandler;
import com.financas.controle.exceptions.IdNaoEncontrado;
import com.financas.controle.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    private final ExceptionsHandler exceptionHandler;



    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest){

        service.cadastrarUsuario(usuarioRequest);

        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> mostrarUsuarios(@RequestParam Long id){

        UsuarioResponse usuario =  service.mostrarUsuarioPeloID(id);

        if (usuario==null){

            throw new IdNaoEncontrado("");
        }

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity<Void> apagarUsuarios(@RequestParam Long id){

        service.deletarUsuario(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping

    public ResponseEntity<Void> atualizarUsuarios (@RequestParam Long id, @RequestBody UsuarioRequest usuarioRequest){

        service.atualizarUsuario(usuarioRequest, id);

        return ResponseEntity.ok().build();
    }
}
