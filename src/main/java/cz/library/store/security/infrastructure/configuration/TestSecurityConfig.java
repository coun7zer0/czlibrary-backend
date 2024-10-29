package cz.library.store.security.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import cz.library.store.security.application.service.PasswordEncoder;
import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.security.infrastructure.service.BCryptEncoder;
import cz.library.store.security.infrastructure.service.JwtService;

@EnableWebSecurity
@Configuration
@Profile("test")
public class TestSecurityConfig {

  @Value("${api.security.secret}")
  private String apiSecret;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().permitAll());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptEncoder();
  }

  @Bean
  public TokenProvider tokenProvider() {
    return new JwtService(apiSecret);
  }

}
