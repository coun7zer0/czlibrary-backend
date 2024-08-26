package cz.library.store.user.application.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.domain.User;
import cz.library.store.validation.domain.Validator;

public class UserValidatorBuilderTest {

  private User user;

  private UserValidatorBuilder validatorBuilder;

  @BeforeEach
  void setUp() {
    validatorBuilder = new UserValidatorBuilder();
    user = new User();
  }

  @Test
  void givenValidUser_whenValidateUserFormat_thenReturnNull() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setName("John");
    user.setLastname("Doe");
    user.setPhoneNumber("+1534541567");

    Validator<User> validator = validatorBuilder
        .userFormat()
        .build();

    // when
    String result = validator.validate(user);

    //then
    assertNull(result);
  }

  @Test
  void givenInvalidField_whentValidateUserFormat_thenReturnMessage() {
    // given
    user.setEmail("jhon.doe@example.com");
    user.setName("John");
    user.setLastname("Doe");
    user.setPhoneNumber("1534541567");

    Validator<User> validator = validatorBuilder
        .userFormat()
        .build();

    //when
    String result = validator.validate(user);

    //then
    assertEquals("Invalid field phoneNumber. The phone number can have minimum 7 digits, "
        + "maximum 15 digits and it must have start with \"+\".", result);
  }

}
