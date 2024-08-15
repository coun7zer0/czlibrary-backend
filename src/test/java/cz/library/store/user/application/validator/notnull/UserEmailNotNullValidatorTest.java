package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserEmailNotNullValidatorTest {

  private final UserValidator validate = new UserEmailNotNullValidator();

  @Test
  void givenUserWithValidEmail_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, "example@mail.com", null, null, null, null, null);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullEmail_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field email. The Email cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
