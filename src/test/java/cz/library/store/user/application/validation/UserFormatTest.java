package cz.library.store.user.application.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cz.library.store.user.domain.User;

public class UserFormatTest {

  private UserFormat validator;
  private User user;

  @BeforeEach
  void setUp() {
    validator = new UserFormat();
    user = new User();
  }

  @Test
  void givenValidUser_whenValidate_thenReturnNull() {
    // given
    user.setEmail("test@example.com");
    user.setPassword("ValidPassword1!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+1234567890");

    // when
    String result = validator.validate(user);

    // then
    assertNull(result);
  }

  @Test
  void givenInvalidEmail_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail("test");

    // when
    String result = validator.validate(user);

    // then
    assertEquals("Invalid field email. The email format does not comply with RFC 2822.", result);
  }

  @Test
  void givenInvalidPassword_whenValidate_thenReturnErrorMessage() {
    // given
    user.setPassword("password");

    // when
    String result = validator.validate(user);

    // then
    assertEquals("Invalid field password. The password must have at least 8 characters, "
        + "1 digit, one uppercase letter, one lowercase letter and one special character.", result);
  }

  @Test
  void givenInvalidName_whenValidate_thenReturnErrorMessage() {
    // given
    user.setName("John Doe1");

    // when
    String result = validator.validate(user);

    // then
    assertEquals("Invalid field name. The name must be capitalized, contain only letters "
        + "and optionally one second name.", result);
  }

  @Test
  void givenInvalidLastname_whenValidate_thenReturnErrorMessage() {
    // given
    user.setLastname("Doe John1");

    // when
    String result = validator.validate(user);

    // then
    assertEquals("Invalid field lastname. The lastname must be capitalized, contain only "
        +"letters and optionally one second lastname.", result);
  }

  @Test
  void givenInvalidPhoneNumber_whenValidate_thenReturnErrorMessage() {
    // given
    user.setPhoneNumber("12345678901");

    // when
    String result = validator.validate(user);

    // then
    assertEquals("Invalid field phoneNumber. The phone number can have minimum 7 digits, "
        + "maximum 15 digits and it must have start with \"+\".", result);
  }

}
