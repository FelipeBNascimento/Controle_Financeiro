package com.financas.controle.dtos;

import com.financas.controle.model.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ConverterDtosParaEntity {

    public UsuarioEntity ConverterParaUsuarioEntity (UsuarioRequest usuario){

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

}
