// ServicoUsuario.java
package br.com.projecao.sigede.servicos;

import br.com.projecao.sigede.dto.CadastroDto;
import br.com.projecao.sigede.dto.CredenciaisDto;
import br.com.projecao.sigede.dto.UsuarioDto;
import br.com.projecao.sigede.excecao.AplicacaoException;
import br.com.projecao.sigede.mapper.MapeadorUsuarios;
import br.com.projecao.sigede.modelos.Usuario;
import br.com.projecao.sigede.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

/**
 * Gerencia as regras de negócio dos usuários
 */
@Service
public class UsuariosServico {

    private final PasswordEncoder codificadorSenha;

    private final MapeadorUsuarios mapeadorUsuarios;
    /**
     * Repositório de usuários
     */
    private final UsuariosRepositorio repositorioUsuario;

    /**
     * Construtor da classe
     *
     * @param codificadorSenha   Codificador de senha
     * @param mapeadorUsuarios   Mapeador de usuários
     * @param repositorioUsuario Repositório de usuários
     */
    @Autowired
    public UsuariosServico(PasswordEncoder codificadorSenha, MapeadorUsuarios mapeadorUsuarios, UsuariosRepositorio repositorioUsuario) {
        this.codificadorSenha = codificadorSenha;
        this.mapeadorUsuarios = mapeadorUsuarios;
        this.repositorioUsuario = repositorioUsuario;
    }

    /**
     * @param credenciaisDto
     * @return
     */
    public UsuarioDto login(CredenciaisDto credenciaisDto) {
        Usuario usuario = repositorioUsuario.findByLogin(credenciaisDto.getLogin())
                .orElseThrow(() -> new AplicacaoException("Login ou Senha inválidos", HttpStatus.NOT_FOUND));

        if (codificadorSenha.matches(CharBuffer.wrap(credenciaisDto.getSenha()), usuario.getSenha())) {
            return mapeadorUsuarios.toUsuarioDto(usuario);
        }
        throw new AplicacaoException("Senha inválida", HttpStatus.BAD_REQUEST);
    }

    /**
     * Salva um usuário
     *
     * @param usuarioDto Dados do usuário
     * @return UsuarioDto - Usuário salvo
     */
    public UsuarioDto salvar(CadastroDto usuarioDto) {
        Optional<Usuario> optionalUser = repositorioUsuario.findByLogin(usuarioDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AplicacaoException("Usuário já existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = mapeadorUsuarios.cadastroDtoToUsuario(usuarioDto);
        usuario.setSenha(codificadorSenha.encode(CharBuffer.wrap(usuarioDto.getSenha())));

        Usuario usuarioSalvo = repositorioUsuario.save(usuario);

        return mapeadorUsuarios.toUsuarioDto(usuarioSalvo);
    }

    public UsuarioDto buscarPorLogin(String login) {
        Usuario usuario = repositorioUsuario.findByLogin(login)
                .orElseThrow(() -> new AplicacaoException("Usuário desconhecido", HttpStatus.NOT_FOUND));
        return mapeadorUsuarios.toUsuarioDto(usuario);
    }

    /**
     * Lista todos os usuários
     *
     * @return List<Usuario> - Lista de usuários
     */
    public List<Usuario> listar() {
        return repositorioUsuario.findAll();
    }

    /**
     * Busca um usuário pelo id
     *
     * @param id ID do usuário
     * @return Usuario - Usuário encontrado
     */
    public Usuario buscarPorId(Long id) {
        return repositorioUsuario.findById(id).orElse(null);
    }

    /**
     * Salva um usuário
     *
     * @param usuario Usuário a ser salvo
     * @return Usuario - Usuário salvo
     */
    public Usuario salvar(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    /**
     * Deleta um usuário
     *
     * @param id ID do usuário a ser deletado
     */
    public void deletar(Long id) {
        repositorioUsuario.deleteById(id);
    }

    /**
     * Atualiza um usuário
     *
     * @param usuario Usuário a ser atualizado
     * @return Usuario - Usuário atualizado
     */
    public Usuario atualizar(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    public List<Usuario> buscarPeloLogin(String login) {
        return repositorioUsuario.findByLoginContaining(login);
    }
}