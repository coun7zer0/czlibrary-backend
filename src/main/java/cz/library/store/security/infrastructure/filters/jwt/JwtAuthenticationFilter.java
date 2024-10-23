package cz.library.store.security.infrastructure.filters.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import cz.library.store.security.application.service.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final TokenProvider jwtTokenProvider;
  private final JpaJwtFilter userDataSource;

  public JwtAuthenticationFilter(TokenProvider jwtTokenProvider, JpaJwtFilter userDataSource) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userDataSource = userDataSource;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = extractToken(request);

    if (token == null
        || !jwtTokenProvider.validateToken(token)
        || SecurityContextHolder.getContext().getAuthentication() != null) {
      filterChain.doFilter(request, response);
      return;
    }

    Long userId = jwtTokenProvider.getUserIdFromJWT(token);
    String username = jwtTokenProvider.getSubject(token);

    if (userId != null && username != null) {
      userDataSource.findById(userId, username)
          .ifPresent(user -> {
            Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
          });
    }

    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      return authorizationHeader.replace("Bearer ", "");
    }
    return null;
  }

}
