package com.financas.controle.service;


import com.financas.controle.dtos.ConverterDtosParaEntity;
import com.financas.controle.dtos.UsuarioRequest;
import com.financas.controle.dtos.UsuarioResponse;
import com.financas.controle.exceptions.IdNaoEncontrado;
import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ConverterDtosParaEntity converterDtosPara;


    // cadastrar usuario
    public void cadastrarUsuario(UsuarioRequest usuario) {

        UsuarioEntity usuarioEntity = converterDtosPara.ConverterParaUsuarioEntity(usuario);

        repository.save(usuarioEntity);
    }

    // buscar usuario pelo id
    public UsuarioResponse mostrarUsuarioPeloID(Long id) {

        UsuarioEntity usuarioBanco = buscarUsauriopeloId(id);
        return converterDtosPara.converterParaUsuarioResponse(usuarioBanco);

    }

    // deletar usuario
    public void deletarUsuario(Long id) {

        repository.deleteById(id);
    }

    // atualizar usuario
    public void atualizarUsuario(UsuarioRequest usuarioRequest, Long id) {

        UsuarioEntity usuarioBanco = buscarUsauriopeloId(id);

        UsuarioEntity usuarioAtualizado = UsuarioEntity.builder()

                .nome(usuarioBanco.getNome().equals(usuarioRequest.nome()) ? usuarioBanco.getNome() : usuarioRequest.nome())
                .email(usuarioBanco.getEmail().equals(usuarioRequest.email()) ? usuarioBanco.getEmail() : usuarioRequest.email())
                .saldo(usuarioRequest.saldo() + usuarioBanco.getSaldo())
                .build();

        repository.saveAndFlush(usuarioAtualizado);

    }

    // atualizar saldo do usuario
    public void atualizarSaldoUsuario(Long id, Double valor){

        UsuarioEntity usuarioEntity = buscarUsauriopeloId(id);

        usuarioEntity.setSaldo(usuarioEntity.getSaldo() - valor);

        repository.saveAndFlush(usuarioEntity);

    }

    // buscar usuario pelo id metodo criado para facilitar busca de usuario
    public UsuarioEntity buscarUsauriopeloId(Long id){

        return repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Usuario não encontrado")
        );
    }


}
