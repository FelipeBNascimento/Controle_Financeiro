package com.financas.controle.dtos;

import com.financas.controle.model.GastosEntity;
import com.financas.controle.model.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDtosParaEntity {

    public UsuarioEntity converterParaUsuarioEntity (UsuarioRequest usuario){

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail(usuario.email());
        usuarioEntity.setNome(usuario.nome());
        usuarioEntity.setSaldo(usuario.saldo());
        usuarioEntity.setSenha(usuario.senha());

        return usuarioEntity;

    }

    public UsuarioResponse converterParaUsuarioResponse(UsuarioEntity usuarioEntity){

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioEntity.getNome(), usuarioEntity.getSaldo());

        return usuarioResponse;
    }

    public GastosEntity converterParaGastosEntity (GastosRequest request){

        GastosEntity gastosEntity = new GastosEntity();
        gastosEntity.setCategoria(request.categoria());
        //gastosEntity.setMotivo(request.motivo());
        gastosEntity.setDescricao(request.descricao());
        gastosEntity.setValor(request.valor());
        gastosEntity.setPagamento(request.pagamento());

        return gastosEntity;


    }

    public GastosResponse gastosResponse (GastosEntity entity){

        GastosResponse gastosResponse = new GastosResponse(entity.getDescricao(), entity.getValor(), LocalDate.now(), entity.getCategoria(), entity.getPagamento());

        return gastosResponse;
    }


    public List<GastosResponse> listaGastos (List<GastosEntity> gastosEntityList){

        List<GastosResponse> listaDeGastos = new ArrayList<>();

        for (GastosEntity gastos : gastosEntityList){

            listaDeGastos.add(gastosResponse(gastos));

        }

        return listaDeGastos;

    }

}
