package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserLastnameNotNullValidatorTest {

  private final UserValidator validate = new UserLastnameNotNullValidator();

  @Test
  void givenUserWithValidLastname_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, null, null, null, "Doe", null, null);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullLastname_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field lastname. The lastname cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
