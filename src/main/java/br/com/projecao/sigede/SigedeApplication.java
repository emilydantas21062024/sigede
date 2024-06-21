package br.com.projecao.sigede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Classe principal da aplicação Sigede
 *
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@EnableJpaAuditing
public class SigedeApplication {

    /**
     * Método principal que inicia a aplicação
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(SigedeApplication.class, args);
    }

}