package cz.library.store.security.application.usecase.login.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.BiPredicate;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.user.domain.User;

public class CredentialsAreValidTest {

  private Function<String, User> userGetterByUsername;

  private TokenRequestData entity;

  @BeforeEach
  void setUp() {
    userGetterByUsername = username -> {
      User user = new User();
      user.setPassword("validPassword123!");
      return user;
    };

    entity = new TokenRequestData("username", "validPassword123!");
  }

  @Test
  void givenValidCredentials_whenValidate_thenReturnNull() {
    // given
    BiPredicate<String, String> matcher = (rawPassword, encriptedPassword) -> true;
    CredentialsAreValid validator = new CredentialsAreValid(userGetterByUsername, matcher);

    //when
    String result = validator.validate(entity);

    //then
    assertNull(result);
  }

  @Test
  void givenInvalidCredentials_whenValidate_thenReturnErrorMessage() {
    // given
    BiPredicate<String, String> matcher = (rawPassword, encriptedPassword) -> false;
    CredentialsAreValid validator = new CredentialsAreValid(userGetterByUsername, matcher);

    //when
    String result = validator.validate(entity);

    //then
    assertEquals("Wrong password", result);
  }

}
