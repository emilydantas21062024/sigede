package br.com.projecao.sigede.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
/**
 *  SecurityConfig class.
 *  This class is responsible for the security configuration of the application.
 *  It uses the UserAuthenticationEntryPoint and UserAuthenticationProvider classes.
 *  It also uses the JwtAuthFilter class.
 *  It disables CSRF and sets the session creation policy to STATELESS.
 *  It also configures the authorization of the requests.
 *  The /, /login, and /register paths are permitted to all users.
 *  All other requests require authentication.
 *  @return SecurityFilterChain
 *  @throws Exception
 *  @see UserAuthenticationEntryPoint
 *  @see UserAuthenticationProvider
 *  @see JwtAuthFilter
 */
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    /**
     * This method configures the security filter chain.
     * It sets the excecao handling to use the userAuthenticationEntryPoint.
     * It adds the JwtAuthFilter before the BasicAuthenticationFilter.
     *
     * @param http HttpSecurity object to configure the security filter chain.
     * @return SecurityFilterChain object. The security filter chain.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/", "/login", "/registrar","/ragistrar*").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }
}
