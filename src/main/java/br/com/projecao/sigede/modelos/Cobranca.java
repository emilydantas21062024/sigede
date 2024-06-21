package br.com.projecao.sigede.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entidade de cobrança
 */
@Entity
@Data
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;

    private Double valor;

    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boleto_id")
    @JsonBackReference
    private Boleto boleto;

    @PrePersist
    public void prePersist() {
        if (data == null) {
            data = LocalDateTime.now().toString();
        }
    }

}