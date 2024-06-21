// ClientesRepositorio.java
package br.com.projecao.sigede.repositorios;

import br.com.projecao.sigede.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface que implementa um repositório de clientes.
 */
public interface ClientesRepositorio extends JpaRepository<Cliente, Long> {
    /**
     * Lista todos os clientes com o nome informado.
     *
     * @param nome Nome do cliente.
     * @return Lista de clientes.
     */
    List<Cliente> findByNome(String nome);
    /**
     * Lista todos os clientes com o nome começando com o nome informado.
     *
     * @param nome Nome do cliente.
     * @return Lista de clientes.
     */
    List<Cliente> findByNomeStartingWith(String nome);
}