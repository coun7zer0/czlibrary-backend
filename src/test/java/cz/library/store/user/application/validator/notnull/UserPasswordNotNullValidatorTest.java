package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserPasswordNotNullValidatorTest {

  private final UserValidator validate = new UserPasswordNotNullValidator();

  @Test
  void givenUserWithValidPassword_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, null, "validPassword123!", null, null, null, null);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullPassword_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field password. The password cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
