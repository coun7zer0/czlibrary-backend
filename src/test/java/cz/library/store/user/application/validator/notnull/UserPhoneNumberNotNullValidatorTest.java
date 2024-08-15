package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserPhoneNumberNotNullValidatorTest {

  private final UserValidator validate = new UserPhoneNumberNotNullValidator();

  @Test
  void givenUserWithValidPhoneNumber_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, null, null, null, null, "+1234567890", null);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullPhoneNumber_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field phoneNumber. The phone number cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
