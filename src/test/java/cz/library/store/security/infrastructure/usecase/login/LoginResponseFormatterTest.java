package cz.library.store.security.infrastructure.usecase.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import cz.library.store.security.application.dto.TokenResponseData;

public class LoginResponseFormatterTest {

  private LoginResponseFormatter formatter;

  @BeforeEach
  void setUp() {
    formatter = new LoginResponseFormatter();
  }

  @Test
  void givenValidTokenResponseData_whenPrepareSuccessView_thenReturnSameTokenResponseData() {
    // given
    TokenResponseData expected = new TokenResponseData("token");

    // when
    TokenResponseData actual = formatter.prepareSuccessView(expected);

    // then
    assertEquals(expected, actual);
  }

  @Test
  public void givenErrorMessage_whenPrepareFailView_thenThrowResponseStatusException() {
    // Given
    String message = "message";

    // When
    ResponseStatusException thrown = assertThrows(
        ResponseStatusException.class,
        () -> formatter.prepareFailView(message));

    // then
    assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());
    assertEquals(message, thrown.getReason());
  }

}
