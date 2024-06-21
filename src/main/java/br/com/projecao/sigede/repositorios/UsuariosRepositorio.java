package br.com.projecao.sigede.repositorios;

import br.com.projecao.sigede.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface que implementa um repositório de usuários.
 */
public interface UsuariosRepositorio extends JpaRepository<Usuario, Long> {
    /**
     * Lista todos os usuários com o login informado.
     *
     * @param login Login do usuário.
     * @return Lista de usuários.
     */
    Optional<Usuario> findByLogin(String login);

    /**
     * Lista todos os usuários com o primeiro nome contendo o nome informado.
     *
     * @param primeiroNome Primeiro nome do usuário.
     * @return Lista de usuários.
     */
    List<Usuario> findByPrimeiroNome(String primeiroNome);

    /**
     * Lista todos os usuários com o login contendo o login informado.
     *
     * @param login Login do usuário.
     * @return Lista de usuários.
     */
    List<Usuario> findByLoginContaining(String login);
}