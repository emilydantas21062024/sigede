package br.com.projecao.sigede.config;

import br.com.projecao.sigede.dto.UsuarioDto;
import br.com.projecao.sigede.servicos.UsuariosServico;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

/**
 * Provedor de autenticação de usuários
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final UsuariosServico usuariosServico;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UsuarioDto user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName", user.getPrimeiroNome())
                .withClaim("lastName", user.getUltimoNome())
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UsuarioDto user = UsuarioDto.builder()
                .login(decoded.getSubject())
                .primeiroNome(decoded.getClaim("firstName").asString())
                .ultimoNome(decoded.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UsuarioDto user = usuariosServico.buscarPorLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

}
