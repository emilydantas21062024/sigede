// ControladorUsuario.java
package br.com.projecao.sigede.controladores;

import br.com.projecao.sigede.config.UserAuthenticationProvider;
import br.com.projecao.sigede.dto.CadastroDto;
import br.com.projecao.sigede.dto.UsuarioDto;
import br.com.projecao.sigede.modelos.Usuario;
import br.com.projecao.sigede.servicos.UsuariosServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosServico servicoUsuario;

    private final UserAuthenticationProvider provedorAutenticacaoUsuario;

    /**
     * Listar todos os usuários
     *
     * @return List<Usuario> - Lista de usuários
     */
    @GetMapping
    public List<Usuario> listar() {
        return servicoUsuario.listar();
    }

    /**
     * Recuperar um usuário pelo Id
     *
     * @param id - Id do usuário
     * @return Usuario - Usuário recuperado
     */
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return servicoUsuario.buscarPorId(id);
    }

    //recuperar um usuário pelo nome
    @GetMapping("/login/{login}")
    public ResponseEntity<List<Usuario>> buscarPeloLogin(@PathVariable String login) {
        return ResponseEntity.ok(servicoUsuario.buscarPeloLogin(login));
    }


    @PostMapping
    public ResponseEntity<UsuarioDto> registrar(@RequestBody @Valid CadastroDto usuario) {
        UsuarioDto usuarioCriado = servicoUsuario.salvar(usuario);

        usuarioCriado.setToken(provedorAutenticacaoUsuario.createToken(usuarioCriado));

        return ResponseEntity.created(URI.create("/usuarios/" + usuarioCriado.getId())).body(usuarioCriado);
    }

    /**
     * Atualizar um usuário
     *
     * @param usuario - Usuário a ser atualizado
     * @return Usuario - Usuário atualizado
     */
    @PutMapping
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return servicoUsuario.atualizar(usuario);
    }

    /**
     * Excluir um usuário
     *
     * @param id - Id do usuário a ser excluído
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        servicoUsuario.deletar(id);
       return  ResponseEntity.noContent().build();
    }
}