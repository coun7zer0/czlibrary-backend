package cz.library.store.security.application.usecase.login.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.BiPredicate;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.user.domain.User;

public class LoginValidatorBuilderTest {

  private TokenRequestData entity;

  public Function<String, User> userGetter;

  private LoginValidatorBuilder validator;

  @BeforeEach
  void setUp() {
    entity = new TokenRequestData("Jhon_Doe", "validPassword123!");
    validator = new LoginValidatorBuilder();
    userGetter = username -> {
      User user = new User();
      user.setPassword("validPassword123!");
      return user;
    };
  }

  @Test
  void givenValidCredentials_whenValidate_thenReturnNull() {
    // given
    BiPredicate<String, String> matcher = (rawPassword, encriptedPassword) -> true;

    // when
    String result = validator.credentialsAreValid(userGetter, matcher).build().validate(entity);

    // then
    assertNull(result);
  }

  @Test
    void givenInvalidCredentials_whenValidate_thenReturnErrorMessage() {
    // given
    BiPredicate<String, String> matcher = (rawPassword, encriptedPassword) -> false;

    // when
    String result = validator.credentialsAreValid(userGetter, matcher).build().validate(entity);

    //then
    assertEquals("Wrong password", result);
  }
}
