package com.financas.controle.service;


import com.financas.controle.dtos.ConverterDtosParaEntity;
import com.financas.controle.dtos.GastosRequest;
import com.financas.controle.exceptions.IdNaoEncontrado;
import com.financas.controle.model.Categoria;
import com.financas.controle.model.GastosEntity;
import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.GastosRepository;
import com.financas.controle.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GastosService {

    private final GastosRepository repository;
    private final ConverterDtosParaEntity converterDtosPara;
    private final UsuarioService usuarioService;

    @Transactional
    public void RegistrarGastos(Long id, GastosRequest request) {

        //buscando um usuario no banco de dados pelo id
        UsuarioEntity usuarioEntity = usuarioService.buscarUsauriopeloId(id);

        // covertendo os gastos que vai ter que passar para o banco
        GastosEntity gastosEntity = converterDtosPara.ConverterParaGastosEntity(request);

        // setando a data do gasto que sempre sera a data do dia que cadastrar
        gastosEntity.setData(LocalDate.now());

        // setando o usuario na tabela dos gastos
        gastosEntity.setUsuario(usuarioEntity);


        // atualizando saldo do usuario
        usuarioService.atualizarSaldoUsuario(id, gastosEntity.getValor());

        repository.save(gastosEntity);

    }

    public void apagarGasto(Long id) {


        repository.deleteById(id);
    }


//    // Essa metodo seria para conferir os gasto
//
// Alterei o metodo todo com uma querie personalizada por sql economizando memoria
//    private BigDecimal somarGastos(Long id, Categoria categoria) {
//
//        // buscando a lista de gasto de um usuario pelo id e pela categoria
//        List<GastosEntity> listaGastos = repository.findAllByUsuarioIdAndCategoria(id, categoria);
//
//        // criando uma lista de somatoria para somar os valores gastos do usuario pela categoria depois
//        List<BigDecimal> listaDeValores = new ArrayList<>();
//
//        // criando ma varivel para dar o retorno depois de somado
//        BigDecimal soma = BigDecimal.ZERO;
//
//        // usando for para adicionar cada valos da lista
//        for (GastosEntity valor : listaGastos) {
//
//            if (valor.getValor() != null) {
//                listaDeValores.add(valor.getValor());
//            }
//
//
//        }
//        // fazendo a somatoria dos valores da lista de valores
//
//        for (BigDecimal valores : listaDeValores) {
//
//            soma.add(valores);
//        }
//
//        return soma;
//
//    }

    private BigDecimal somarGastos (Categoria categoria, Long id) {

        BigDecimal valores = repository.somarGastosPorCategoria(categoria, id);

        if(valores!= null){
            return valores;
        }else {
            return BigDecimal.ZERO;
        }

    }
    public BigDecimal mostrarValoresGastos(Long id, Categoria categoria) {

        return somarGastos(categoria, id);
    }

    public List<GastosEntity> mostrarCategoria(Categoria categoria) {

        List<GastosEntity> lista = repository.findAllByCategoria(categoria);

        return lista;

    }

    public void excluirGastos(Long id) {

        repository.deleteById(id);

    }
}

