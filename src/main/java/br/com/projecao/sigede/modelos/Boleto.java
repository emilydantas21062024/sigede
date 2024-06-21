package br.com.projecao.sigede.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entidade de boleto
 */
@Entity
@Data
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;

    private Double valor;

    private Double desconto;

    private Double valorFinal;

    private String descricao;

    private String dataVencimento;

    private String dataPagamento;

    private String status;

    private String observacao;

    private String parcela;

    private String metodoPagamento;

    private Integer diasAtraso;

    private String nomeCliente;

    private String cpfCnpjCliente;

    private Double multa;

    private Double juros;

    @OneToMany(mappedBy = "boleto")
    @JsonManagedReference
    private List<Cobranca> cobrancas;

}