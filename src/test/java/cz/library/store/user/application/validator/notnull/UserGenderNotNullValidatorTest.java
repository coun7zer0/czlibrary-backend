package cz.library.store.user.application.validator.notnull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.Gender;
import cz.library.store.user.domain.User;

public class UserGenderNotNullValidatorTest {

  private final UserValidator validate = new UserGenderNotNullValidator();

  @Test
  void givenUserWithValidGender_whenValidate_thenReturnsNull() {
    // given
    User user = new User(null, null, null, null, null, null, Gender.MALE);

    // when
    String result = validate.validate(user);
    
    // then
    assertEquals(null, result);
  }

  @Test
  void givenUserWithNullGender_whenValidate_thenReturnsErrorMessage() {
    // given
    User user = new User();
    String errorMessage = "Invalid field gender. The gender cannot be null.";
    
    // when
    String result = validate.validate(user);

    // then
    assertEquals(errorMessage, result);
  }
}
