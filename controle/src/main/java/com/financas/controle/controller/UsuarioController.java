package com.financas.controle.controller;

import com.financas.controle.Config.security.JwtUtil;
import com.financas.controle.Config.security.SecurityConfig;
import com.financas.controle.dtos.UsuarioRequest;
import com.financas.controle.dtos.UsuarioResponse;
import com.financas.controle.exceptions.ExceptionsHandler;
import com.financas.controle.exceptions.IdNaoEncontrado;
import com.financas.controle.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor

@Tag(name = "usuarios", description = "Controle para salvar editar dados de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UsuarioController {

    private final UsuarioService service;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;




    @PostMapping
    @Operation(summary = "Cadastrar usuarios", description = "cadastrar usuario ao banco de dados")
    @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Falha ao cadastrar usuario")
    public ResponseEntity<Void> cadastrarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){

        service.cadastrarUsuario(usuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PostMapping("/login")
    @Operation(summary = "Logar com usuario", description = "Fazer login com usuario")
    @ApiResponse(responseCode = "200",description = "Logado com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    public String login(@RequestBody UsuarioRequest usuarioDTO){

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.email(), usuarioDTO.senha()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());

    }
    @GetMapping

    public ResponseEntity<UsuarioResponse> mostrarUsuarios(@RequestParam Long id){

        UsuarioResponse usuario =  service.mostrarUsuarioPeloID(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity<Void> apagarUsuarios(@RequestParam Long id){

        service.deletarUsuario(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados usuario", description = "Informar novos dados atualizados")
    @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados")
    public ResponseEntity<Void> atualizarUsuarios (@RequestParam Long id, @RequestBody UsuarioRequest usuarioRequest){

        service.atualizarUsuario(usuarioRequest, id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/saldo")
    @Operation(summary = "Adicionar saldo usuario", description = "Informar valores para adicionar")
    @ApiResponse(responseCode = "200", description = "saldo adicionado com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "400", description = "Erro ao adicionar saldo")
    public ResponseEntity<Void> adizionarSaldo(@PathVariable Long id, @RequestParam BigDecimal saldo){

        service.adicionarSaldo(id, saldo);

        return ResponseEntity.ok().build();
    }
}
