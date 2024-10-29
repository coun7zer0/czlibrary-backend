package cz.library.store.security.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cz.library.store.security.application.service.PasswordEncoder;
import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.security.infrastructure.filters.jwt.JpaJwtFilter;
import cz.library.store.security.infrastructure.filters.jwt.JwtAuthenticationFilter;
import cz.library.store.security.infrastructure.service.BCryptEncoder;
import cz.library.store.security.infrastructure.service.JwtService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Value("${api.security.secret}")
  private String apiSecret;

  private final JpaJwtFilter jwtDataSource;

  public SecurityConfig(JpaJwtFilter jwtDataSource) {
    this.jwtDataSource = jwtDataSource;
  }

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated())
        .addFilterBefore(
            new JwtAuthenticationFilter(TokenProvider(), jwtDataSource),
            UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptEncoder();
  }

  @Bean
  public TokenProvider TokenProvider() {
    return new JwtService(apiSecret);
  }
}
