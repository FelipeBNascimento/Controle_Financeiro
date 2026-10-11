package com.financas.controle.service;


import com.financas.controle.Config.security.JwtUtil;
import com.financas.controle.dtos.ConverterDtosParaEntity;
import com.financas.controle.dtos.UsuarioRequest;
import com.financas.controle.dtos.UsuarioResponse;
import com.financas.controle.exceptions.IdNaoEncontrado;
import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ConverterDtosParaEntity converterDtosPara;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // cadastrar usuario
    public void cadastrarUsuario(UsuarioRequest usuario) {


        UsuarioEntity usuarioEntity = converterDtosPara.ConverterParaUsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha()));

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
                .saldo(usuarioBanco.getSaldo().equals(usuarioRequest.saldo())? usuarioBanco.getSaldo(): usuarioRequest.saldo())
                .build();

        repository.saveAndFlush(usuarioAtualizado);

    }

    // adicionarSaldo

    @Transactional
    public void adicionarSaldo(Long id, BigDecimal saldo){

        UsuarioEntity usuarioEntity = buscarUsauriopeloId(id);

        usuarioEntity.setSaldo(usuarioEntity.getSaldo().add(saldo));

        repository.save(usuarioEntity);

    }

    // atualizar saldo do usuario
    @Transactional
    public void atualizarSaldoUsuario(Long id, BigDecimal valor){

        UsuarioEntity usuarioEntity = buscarUsauriopeloId(id);

        usuarioEntity.setSaldo(usuarioEntity.getSaldo().subtract(valor));

        repository.save(usuarioEntity);

    }

    // buscar usuario pelo id metodo criado para facilitar busca de usuario
    public UsuarioEntity buscarUsauriopeloId(Long id){

        return repository.findById(id).orElseThrow(
                () -> new IdNaoEncontrado("Usuario não encontrado")
        );
    }


}
