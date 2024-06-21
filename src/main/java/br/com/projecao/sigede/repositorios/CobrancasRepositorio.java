package br.com.projecao.sigede.repositorios;

import br.com.projecao.sigede.modelos.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface que implementa um repositório de cobranças.
 */
public interface CobrancasRepositorio extends JpaRepository<Cobranca, Long> {
    /**
     * Lista todas as cobranças com a descrição contendo a descrição informada.
     *
     * @param descricao Descrição da cobrança.
     * @return Lista de cobranças.
     */
    List<Cobranca> findByDescricaoContaining(String descricao);

    /**
     * Lista todas as cobranças com a data igual a data informada.
     *
     * @param data Data da cobrança.
     * @return Lista de cobranças.
     */
    List<Cobranca> findByData(String data);

    /**
     * Lista todas as cobranças com o id do boleto igual ao id  informado.
     *
     * @param idBoleto ID do boleto.
     * @return Lista de cobranças.
     */
    List<Cobranca> findByBoleto_Id(Long idBoleto);
}