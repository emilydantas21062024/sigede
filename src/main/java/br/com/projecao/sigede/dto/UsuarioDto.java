package br.com.projecao.sigede.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de transferência de dados para Usuário
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;
    private String primeiroNome;
    private String ultimoNome;
    private String login;
    private String token;
    private String papel;
}