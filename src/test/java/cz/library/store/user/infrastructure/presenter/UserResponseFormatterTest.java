package cz.library.store.user.infrastructure.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.domain.User;

public class UserResponseFormatterTest {

  public final UserResponseFormatter formatter = new UserResponseFormatter();

  @Test
  public void givenUserResponseData_whenPrepareSuccessView_ThenReturnSameUserResponseData() {
    // Given
    UserResponseData user = new UserResponseData(new User());

    // When
    UserResponseData result = formatter.prepareSuccessView(user);

    // Then
    assertEquals(user, result);
  }

  @Test
  public void givenErrorMessage_whenPrepareFailViewWithMessage_thenThrowResponseStatusException() {
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

  @Test
  public void whenPrepareFailViewWithoutMessage_thenThrowResponseStatusException() {
    // When
    ResponseStatusException thrown = assertThrows(
        ResponseStatusException.class,
        () -> formatter.prepareFailView());

    // Then
    assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());
  }

}
