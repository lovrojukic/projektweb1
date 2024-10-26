package projekt.jedan.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import projekt.jedan.service.IdHandler;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IdHandler idHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/oauth2/**", "/api/tickets/count").permitAll()
                .antMatchers("/ticket/view/**").permitAll()
                .antMatchers("/api/tickets/view/**").authenticated()
                .antMatchers("/api/tickets/create").hasAuthority("SCOPE_create:ticket")
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login.html")
                .successHandler(idHandler)
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        return JwtDecoders.fromIssuerLocation("https://dev-zxy3jw1p6co4h8t5.eu.auth0.com/");
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        return jwtAuthenticationConverter;
    }
}

