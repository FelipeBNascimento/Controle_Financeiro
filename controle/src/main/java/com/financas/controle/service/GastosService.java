package com.financas.controle.service;


import com.financas.controle.dtos.ConverterDtosParaEntity;
import com.financas.controle.dtos.GastosRequest;
import com.financas.controle.model.GastosEntity;
import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.GastosRepository;
import com.financas.controle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GastosService {

    private final GastosRepository repository;
    private final ConverterDtosParaEntity converterDtosPara;
    private final UsuarioRepository usuarioRepository;

    public void RegistrarGastos(Long id, GastosRequest request){

        // covertendo osgastos que vai ter que passar para o banco
        GastosEntity gastosEntity = converterDtosPara.ConverterParaGastosEntity(request);

        // setando a data do gasto que sempre sera a data do dia que cadastrar
        gastosEntity.setData(LocalDate.now());
        //buscando um usuario no banco de dados pelo id
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow();

        // setando o usuario na tabela dos gastos
        gastosEntity.setUsuario(usuarioEntity);

        repository.save(gastosEntity);

    }

    public void apagarGasto(Long id){



        repository.deleteById(id);
    }

    public Double somarGastos(Long id){

        // buscando a lista de gasto pelo usuario
        List<GastosEntity> listaGastos = repository.findAllByUsuarioId(id);

        // criando uma lista de somatoria para somar os valores gastos depois
        List<Double> somatoria = new ArrayList<>();

        // criando ma varivel para dar o retorno depois de somado
        Double soma = 0.0;

        // usando for para adicionar cada valos da lista
       for (GastosEntity valor : listaGastos){

           if(valor.getValor() != null){
               somatoria.add(valor.getValor());
           }


       }
        // fazendo a somatoria dos valos da lista de valores

        for (Double valores : somatoria){

            soma+=valores;
        }

        return soma;

    }

    public Double MostrarValoresGastos(Long id){

        return somarGastos(id);
    }
}
