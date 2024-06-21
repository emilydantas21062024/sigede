package br.com.projecao.sigede.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Usuario
 *  Classe de modelo para usuário
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primeiro_nome", nullable = false)
    @Size(max = 100)
    private String primeiroNome;

    @Column(name = "ultimo_nome", nullable = false)
    @Size(max = 100)
    private String ultimoNome;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String senha;

    @Column(nullable = false)
    @Size(max = 100)
    private String papel = "ASSISTENTE";
}