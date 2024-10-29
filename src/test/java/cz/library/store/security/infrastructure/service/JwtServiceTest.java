package cz.library.store.security.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.library.store.user.domain.User;

public class JwtServiceTest {

  private final String API_SECRET = "ApiSecretKeyTooLongToBeUsedAsSecretKeyInTests";

  private JwtService jwtService;

  private User mockUser;

  @BeforeEach
  void setUp() {
    jwtService = new JwtService(API_SECRET);
    mockUser = mock(User.class);
    when(mockUser.getId()).thenReturn(1L);
  }

  // generateToken
  @Test
  void givenValidUser_whenGenerateToken_thenTokenShouldBeGenerated() {
    // given
    String username = "testUser";

    // when
    String token = jwtService.generateToken(mockUser, username);

    // then
    assertNotNull(token);
    assertTrue(token.startsWith("ey"));
  }

  // validateToken
  @Test
  void givenValidToken_whenValidateToken_thenReturnTrue() {
    // given
    String username = "testUser";
    String token = jwtService.generateToken(mockUser, username);

    // when
    boolean isValid = jwtService.validateToken(token);

    // then
    assertTrue(isValid);
  }

  @Test
  void givenInvalidToken_whenValidateToken_thenReturnFalse() {
    // given
    String token = "invalidToken";

    // when
    boolean isValid = jwtService.validateToken(token);

    // then
    assertFalse(isValid);
  }

  // getSubject
  @Test
  void givenValidToken_whenGetSubject_thenReturnUsername() {
    // given
    String username = "testUser";
    String token = jwtService.generateToken(mockUser, username);

    // when
    String subject = jwtService.getSubject(token);

    // then
    assertEquals(username, subject);
  }

  @Test
  void givenInvalidToken_whenGetSubject_thenReturnNull() {
    // given
    String token = "invalidToken";

    // when
    String subject = jwtService.getSubject(token);

    // then
    assertNull(subject);
  }

  //getUserIdFromToken
  @Test
  void givenValidToken_whenGetUserIdFromToken_thenReturnUserId() {
    // given
    String username = "testUser";
    String token = jwtService.generateToken(mockUser, username);

    // when
    Long userId = jwtService.getUserIdFromJWT(token);

    // then
    assertEquals(1L, userId);
  }

  @Test
  void givenInvalidToken_whenGetUserIdFromToken_thenReturnNull() {
    // given
    String token = "invalidToken";

    // when
    Long userId = jwtService.getUserIdFromJWT(token);

    // then
    assertNull(userId);
  }

}
