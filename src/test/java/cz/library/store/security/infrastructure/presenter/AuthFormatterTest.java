package cz.library.store.security.infrastructure.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.user.domain.User;

public class AuthFormatterTest {

  public AuthFormatter authFormatter;

  @BeforeEach
  public void setUp() {
    authFormatter = new AuthFormatter();
  }

  @Test
  public void givenAuthData_whenPrepareSuccessView_thenReturnsSameAuthData() {
    // given
    User user = new User();
    Function<User, String> usernameGetter = User::getEmail;
    AuthData authData = new AuthData(user, usernameGetter);

    // when
    AuthData result = authFormatter.prepareSuccessView(authData);

    // then
    assertEquals(user, result.user());
    assertEquals(usernameGetter, result.usernameGetter());
  }

  @Test
  public void givenErrorMessage_whenPrepareFail_thenThrowUsernameNotFoundException() {
    // given
    String message = "message";

    // when
    UsernameNotFoundException thrown = assertThrows(
        UsernameNotFoundException.class,
        () -> authFormatter.prepareFailView(message));

    // then
    assertEquals(message, thrown.getMessage());
  }
}
