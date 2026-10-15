package com.financas.controle.controller;


import com.financas.controle.dtos.GastosRequest;
import com.financas.controle.dtos.GastosResponse;
import com.financas.controle.model.Categoria;
import com.financas.controle.service.GastosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/gastos")
public class GastosController {

    private final GastosService service;

    @PostMapping("{id}")
    public ResponseEntity<Void> cadastrarGastos(@Valid @RequestBody GastosRequest gastosRequest, @PathVariable Long id){


        service.RegistrarGastos(id, gastosRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BigDecimal> mostrarTotalGasto(@PathVariable Long id, @RequestParam Categoria categoria){

        return ResponseEntity.ok(service.mostrarValoresGastos(id, categoria));
    }

    @GetMapping
    public ResponseEntity<List<GastosResponse>> mostrarListaDeGastos (@RequestParam Categoria categoria){

        return ResponseEntity.ok(service.mostrarCategoria(categoria));
    }
}
