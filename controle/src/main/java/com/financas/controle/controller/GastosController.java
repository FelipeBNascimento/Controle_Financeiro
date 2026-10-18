package com.financas.controle.controller;


import com.financas.controle.Config.security.SecurityConfig;
import com.financas.controle.dtos.GastosRequest;
import com.financas.controle.dtos.GastosResponse;
import com.financas.controle.model.Categoria;
import com.financas.controle.service.GastosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gastos")

@Tag(name = "gastos", description = "Controle para salvar editar gastos de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class GastosController {

    private final GastosService service;

    @PostMapping("{id}")
    @Operation(summary = "Cadastrar algum gasto para usuario", description = "Cadastrar gasto do usuario")
    @ApiResponse(responseCode = "201", description = "Gasto cadastrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "400", description = "Erro ao cadastrar gasto faltou alguma informação")
    public ResponseEntity<Void> cadastrarGastos(@Valid @RequestBody GastosRequest gastosRequest, @PathVariable Long id){


        service.RegistrarGastos(id, gastosRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    @Operation(summary = "Mostrar valores gasto por categoria", description = "Valores gastos por categoria")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "400", description = "Erro ao vizualizar gastos")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "200", description = "Visualização com exito")

    public ResponseEntity<BigDecimal> mostrarTotalGasto(@PathVariable Long id, @RequestParam Categoria categoria){

        return ResponseEntity.ok(service.mostrarValoresGastos(id, categoria));
    }

    @GetMapping
    @Operation(summary = "Visualizar listas de gastos por categoria", description = "Vizualizar listas de gastos por categoria")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "200", description = "Visualização com exito")

    public ResponseEntity<List<GastosResponse>> mostrarListaDeGastos (@RequestParam Categoria categoria){

        return ResponseEntity.ok(service.mostrarCategoria(categoria));
    }
}
