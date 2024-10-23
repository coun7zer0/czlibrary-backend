package cz.library.store.security.infrastructure.filters.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.security.infrastructure.model.AuthDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilterTest {

  @Mock
  private TokenProvider jwtTokenProvider;

  @Mock
  private JpaJwtFilter userDataSource;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @InjectMocks
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    SecurityContextHolder.clearContext();
  }

  @Test
  void givenValidToken_whenDoFilterInternal_thenSetAuthentication()
      throws ServletException, IOException {
    // given
    String token = "valid.jwt.token";
    Long userId = 1L;
    String username = "jhon.doe@example.com";
    AuthDetails authDetails = new AuthDetails(null, u -> username);

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtTokenProvider.validateToken(token)).thenReturn(true);
    when(jwtTokenProvider.getUserIdFromJWT(token)).thenReturn(userId);
    when(jwtTokenProvider.getSubject(token)).thenReturn(username);
    when(userDataSource.findById(userId, username))
        .thenReturn(Optional.of(authDetails));

    // when
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // then
    verify(userDataSource).findById(userId, username);
    verify(filterChain).doFilter(request, response);

    UsernamePasswordAuthenticationToken authentication =
        (UsernamePasswordAuthenticationToken) SecurityContextHolder
            .getContext()
            .getAuthentication();

    assertEquals(authDetails, authentication.getPrincipal());
  }

  @Test
  void givenInvalidToken_whenDoFilterInternal_thenDoNotSetAuthentication()
    throws ServletException, IOException {
    // given
    String token = "invalid.jwt.token";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtTokenProvider.validateToken(token)).thenReturn(false);

    // when
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // then
    verify(jwtTokenProvider).validateToken(token);
    verify(filterChain).doFilter(request, response);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void givenNoToken_whenDoFilterInternal_thenDoNotSetAuthentication()
      throws ServletException, IOException {
    // given
    when(request.getHeader("Authorization")).thenReturn(null);

    // when
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // then
    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void givenTokenButUserNotFound_whenDoFilterInternal_thenDoNotSetAuthentication()
      throws ServletException, IOException {
    // given
    String token = "valid.jwt.token";
    Long userId = 1L;
    String username = "jhon.doe@example.com";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtTokenProvider.validateToken(token)).thenReturn(true);
    when(jwtTokenProvider.getUserIdFromJWT(token)).thenReturn(userId);
    when(jwtTokenProvider.getSubject(token)).thenReturn(username);
    when(userDataSource.findById(userId, username)).thenReturn(Optional.empty());

    //when
    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    // then
    verify(userDataSource).findById(userId, username);
    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

}
