package br.com.projecao.sigede.controladores;

import br.com.projecao.sigede.config.UserAuthenticationProvider;
import br.com.projecao.sigede.dto.CadastroDto;
import br.com.projecao.sigede.dto.CredenciaisDto;
import br.com.projecao.sigede.dto.UsuarioDto;
import br.com.projecao.sigede.servicos.UsuariosServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Controlador de autenticação
 */
@RequiredArgsConstructor
@RestController
public class AutenticacaoController {

    /**
     * Serviço de usuários
     */
    private final UsuariosServico usuariosServico;
    /**
     * Provedor de autenticação
     */
    private final UserAuthenticationProvider userAuthenticationProvider;

    /**
     * Realiza o login
     *
     * @param credenciaisDto Credenciais do usuário
     * @return Usuário autenticado
     */
    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid CredenciaisDto credenciaisDto) {
        UsuarioDto usuarioDto = usuariosServico.login(credenciaisDto);
        usuarioDto.setToken(userAuthenticationProvider.createToken(usuarioDto));
        return ResponseEntity.ok(usuarioDto);
    }

    /**
     * Realiza o registro
     *
     * @param usuario Usuário a ser registrado
     * @return Usuário registrado
     */
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> registrar(@RequestBody @Valid CadastroDto usuario) {
        UsuarioDto usuarioCriado = usuariosServico.salvar(usuario);
        usuarioCriado.setToken(userAuthenticationProvider.createToken(usuarioCriado));
        return ResponseEntity.created(URI.create("/" + usuarioCriado.getId())).body(usuarioCriado);
    }

}
