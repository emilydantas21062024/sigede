package br.com.projecao.sigede.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastroDto {

    @NotEmpty
    private String primeiroNome;

    @NotEmpty
    private String ultimoNome;

    @NotEmpty
    private String login;

    @NotEmpty
    private char[] senha;

    @NotEmpty
    private String papel;

}