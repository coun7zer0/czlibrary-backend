package cz.library.store.security.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Profile("!test")
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorize -> authorize
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/v1/users").permitAll()
                .anyRequest().denyAll());

    return http.build();
  }
}
