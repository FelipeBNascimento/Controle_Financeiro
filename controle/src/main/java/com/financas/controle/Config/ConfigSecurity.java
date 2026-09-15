package com.financas.controle.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para simplificar em APIs REST. Cuidado em apps com HTML forms.
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Apenas o GET para /produtos (mostrar estoque) requer autenticação
                                //.requestMatchers(HttpMethod.GET, "/produtos").authenticated()
                                //.requestMatchers(HttpMethod.POST, "/produtos").authenticated()
                                .anyRequest().permitAll() // Todas as outras requisições (POST, PUT, DELETE, GET por ID) são permitidas sem autenticação
                )
                .formLogin(formLogin ->
                        formLogin
                                .permitAll()
                )
                .httpBasic(httpBasic -> {
                }); // Habilita autenticação HTTP Basic para APIs

        return http.build();
    }

}

