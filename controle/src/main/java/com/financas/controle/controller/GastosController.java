package com.financas.controle.controller;


import com.financas.controle.dtos.GastosRequest;
import com.financas.controle.service.GastosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/vi/gastos")
public class GastosController {

    private final GastosService service;

    @PostMapping
    public ResponseEntity<Void> cadastrarGastos(@RequestBody GastosRequest gastosRequest, @RequestParam Long id){


        service.RegistrarGastos(id, gastosRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Double> mostrarTotalGasto(@RequestParam Long id){

        return ResponseEntity.ok(service.somarGastos(id));
    }
}
