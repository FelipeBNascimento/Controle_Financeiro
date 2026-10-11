package com.financas.controle.Config.security;

import com.financas.controle.model.UsuarioEntity;
import com.financas.controle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("Usuario não existe" + email)
        );

        return org.springframework.security.core.userdetails.User
                .withUsername(usuarioEntity.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioEntity.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails

    }
}