// BoletosRepositorio.java
package br.com.projecao.sigede.repositorios;

import br.com.projecao.sigede.modelos.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface que implementa um repositório de boletos.
 */
public interface BoletosRepositorio extends JpaRepository<Boleto, Long> {

    /**
     * Lista todas as dívidas com o status informado.
     *
     * @param status Status da dívida.
     * @return Lista de dívidas.
     */
    List<Boleto> findByStatus(String status);


    /**
     * Lista todas as dívidas que não possuem cobrança.
     * @return
     */
    List<Boleto> findAllByCobrancasIsNull();
}




