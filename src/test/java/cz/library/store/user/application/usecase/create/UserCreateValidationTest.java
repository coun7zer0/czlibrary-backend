package cz.library.store.user.application.usecase.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.domain.User;

public class UserCreateValidationTest {

  @Mock
  private UserDataSourceGateway userDataSource;

  private UserCreateValidation validation;

  private User user;

  @BeforeEach
    void setUp () {
    MockitoAnnotations.openMocks(this);
    validation = new UserCreateValidation(userDataSource);
    user = new User();
  }

  @Test
  void givenValidUser_whenValidate_thenReturnNull() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setPassword("ValidPassword123!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+420123456789");

    when(userDataSource.existsByEmail("jhon.doe@example.com")).thenReturn(false);
    when(userDataSource.existsByPhoneNumber("+420123456789")).thenReturn(false);

    // when
    String result = validation.validate(user);

    // then
    assertNull(result);
  }

  @Test
  void givenNullPassword_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setPassword(null);
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+420123456789");

    // when
    String result = validation.validate(user);

    // then
    assertEquals("Invalid field password. The password cannot be null.", result);
  }

  @Test
  void givenNullEmailAndPhoneNumber_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail(null);
    user.setPassword("ValidPassword123!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber(null);

    // when
    String result = validation.validate(user);

    // then
    assertEquals("You must provide either email or phoneNumber.", result);
  }

  @Test
  void givenInvalidEmailFormat_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail("@example.com");
    user.setPassword("ValidPassword123!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+420123456789");

    // when
    String result = validation.validate(user);

    // then
    assertEquals("Invalid field email. The email format does not comply with RFC 2822.", result);
  }

  @Test
  void givenExistingEmail_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setPassword("ValidPassword123!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+420123456789");

    when(userDataSource.existsByEmail("jhon.doe@example.com")).thenReturn(true);
    when(userDataSource.existsByPhoneNumber("+420123456789")).thenReturn(false);

    // when
    String result = validation.validate(user);

    // then
    assertEquals("Invalid field email. The email already exists in the database.", result);
  }

  @Test
  void givenExistingPhoneNumber_whenValidate_thenReturnErrorMessage() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setPassword("ValidPassword123!");
    user.setName("Jhon");
    user.setLastname("Doe");
    user.setPhoneNumber("+420123456789");

    when(userDataSource.existsByEmail("jhon.doe@example.com")).thenReturn(false);
    when(userDataSource.existsByPhoneNumber("+420123456789")).thenReturn(true);

    // when
    String result = validation.validate(user);

    // then
    assertEquals("Invalid field phoneNumber. The phoneNumber already exists in the database.", result);
  }

}
