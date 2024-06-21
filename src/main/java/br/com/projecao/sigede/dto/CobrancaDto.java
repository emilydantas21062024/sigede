package br.com.projecao.sigede.dto;

import br.com.projecao.sigede.modelos.Boleto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe de transferência de dados para Cobrança
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CobrancaDto {
    private Long id;
    private String descricao;
    private String data;
    private Boleto boleto;
}