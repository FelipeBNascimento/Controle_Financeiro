package com.financas.controle.service;


import com.financas.controle.dtos.ConverterDtosParaEntity;
import com.financas.controle.dtos.UsuarioRequest;
import com.financas.controle.dtos.UsuarioResponse;
import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ConverterDtosParaEntity converterDtosPara;


    public void cadastrarUsuario(UsuarioRequest usuario) {

        UsuarioEntity usuarioEntity = converterDtosPara.ConverterParaUsuarioEntity(usuario);

        repository.save(usuarioEntity);
    }

    public UsuarioResponse mostrarUsuarioPeloID(Long id) {

        UsuarioEntity usuarioBanco = repository.findById(id).orElseThrow();

        return converterDtosPara.converterParaUsuarioResponse(usuarioBanco);

    }

    public void deletarUsuario(Long id) {

        repository.deleteById(id);
    }

    public void atualizarUsuario(UsuarioRequest usuarioRequest, Long id) {


        UsuarioEntity usuarioBanco = repository.findById(id).orElseThrow();

        UsuarioEntity usuarioAtualizado = UsuarioEntity.builder()

                .nome(usuarioBanco.getNome().equals(usuarioRequest.nome()) ? usuarioBanco.getNome() : usuarioRequest.nome())
                .email(usuarioBanco.getEmail().equals(usuarioRequest.email()) ? usuarioBanco.getEmail() : usuarioRequest.email())
                .saldo(usuarioRequest.saldo() + usuarioBanco.getSaldo())
                .build();


    }


}
