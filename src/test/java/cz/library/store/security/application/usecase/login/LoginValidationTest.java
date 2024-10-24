package cz.library.store.security.application.usecase.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.service.PasswordEncoder;
import cz.library.store.user.domain.User;

public class LoginValidationTest {

  @Mock
  private LoginDataSourceGateway userDataSource;

  @Mock
  private PasswordEncoder encoder;

  @InjectMocks
  private LoginValidation validation;

  private TokenRequestData requestData;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    requestData = new TokenRequestData("Jhon_Doe", "validPassword123!");
    user = new User();
    user.setPassword("validPassword123!");
  }

  @Test
  void givenValidCredentials_whenValidate_thenReturnNull() {
    // given
    when(userDataSource.existsByUsername("Jhon_Doe")).thenReturn(true);
    when(userDataSource.findByUsername("Jhon_Doe")).thenReturn(user);
    when(encoder.matches("validPassword123!", "validPassword123!")).thenReturn(true);

    // when
    String result = validation.validate(requestData);

    // then
    assertNull(result);
  }

  @Test
  void givenNullUsername_whenValidate_thenReturnErrorMessage() {
    // given
    requestData = new TokenRequestData(null, "validPassword123");

    // when
    String result = validation.validate(requestData);

    // then
    assertEquals("Invalid field username. The username cannot be null.", result);
  }

  @Test
  void givenNullPassword_whenValidate_thenReturnErrorMessage() {
    // given
    requestData = new TokenRequestData("Jhon_Doe", null);

    // when
    String result = validation.validate(requestData);

    // then
    assertEquals("Invalid field password. The password cannot be null.", result);
  }

  @Test
  void givenInexistentUsername_whenValidate_thenReturnErrorMessage() {
    // given
    when(userDataSource.existsByUsername("Jhon_Doe")).thenReturn(false);

    // when
    String result = validation.validate(requestData);

    // then
    assertEquals("Invalid field username. The username does not exist in the database.", result);
  }

  @Test
  void givenWrongPassword_whenValidate_thenReturnErrorMessage() {
    // given
    when(userDataSource.existsByUsername("Jhon_Doe")).thenReturn(true);
    when(userDataSource.findByUsername("Jhon_Doe")).thenReturn(user);
    when(encoder.matches("validPassword123!", "validPassword123!")).thenReturn(false);

    // when
    String result = validation.validate(requestData);

    // then
    assertEquals("Wrong password", result);
  }

}
