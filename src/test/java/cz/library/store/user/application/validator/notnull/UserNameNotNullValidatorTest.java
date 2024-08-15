package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserNameNotNullValidatorTest {

  private final UserValidator validate = new UserNameNotNullValidator();

  @Test
  void givenUserWithValidName_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, null, null, "Jhon", null, null, null);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullName_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field name. The name cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
