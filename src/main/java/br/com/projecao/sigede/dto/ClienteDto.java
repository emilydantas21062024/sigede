package br.com.projecao.sigede.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de transferência de dados para Cliente

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    private Long id;

    private String nome;

    String cpfCnpj;

    String endereco;

    String telefone;

    String email;

    String cidade;

    String estado;

    String cep;

    String dataNascimento;
}
