package br.com.projecao.sigede.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *  Entidade de cliente
 */
@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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