package br.com.projecao.sigede.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *  PasswordConfig
 *  Classe de configuração para criptografia de senha
 *  @see org.springframework.security.crypto.password.PasswordEncoder
 */
@Component
public class PasswordConfig {

    /**
     * Método que retorna um objeto de criptografia de senha
     * @return BCryptPasswordEncoder - Objeto de criptografia de senha
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
