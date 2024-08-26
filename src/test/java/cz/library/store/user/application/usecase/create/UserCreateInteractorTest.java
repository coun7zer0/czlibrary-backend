package cz.library.store.user.application.usecase.create;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.presenter.UserPresenter;
import cz.library.store.user.domain.User;

public class UserCreateInteractorTest {

  @Mock
  private UserPresenter userPreseter;

  @Mock
  private UserCreateValidation userValidation;

  @Mock
  private UserCreateDataSourceGateway userDataSource;

  private UserCreateInteractor interactor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    interactor = new UserCreateInteractor(userPreseter, userValidation, userDataSource);
  }

  @Test
  void givenValidUserData_whenCreateUser_thenSaveItAndPrepareSuccessView() {
    // given
    User user = new User(null, "example@mail.com", "validPassword123!", null, null, null, null);
    UserRequestData userRequestData = new UserRequestData(
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getLastname(),
        user.getPhoneNumber(),
        user.getGender());

    when(userValidation.validate(any(User.class))).thenReturn(null);
    when(userDataSource.save(any(User.class))).thenReturn(user);

    // when
    interactor.create(userRequestData);

    // then
    verify(userValidation, times(1)).validate(any(User.class));
    verify(userDataSource, times(1)).save(any(User.class));
    verify(userPreseter, times(1)).prepareSuccessView(any(UserResponseData.class));
  }

  @Test
  void givenNullUserData_whenCreateUser_thenPrepareFailView() {
    // given
    UserRequestData userRequestData = null;

    // when
    interactor.create(userRequestData);

    // then
    verify(userValidation, times(0)).validate(any(User.class));
    verify(userDataSource, times(0)).save(any(User.class));
    verify(userPreseter, times(1)).prepareFailView("Missing data");
  }

  @Test
  void givenInvalidUserDate_whenCreateUser_thenPrepareFailView() {
    // given
    User user = new User(null, "example@mail.com", null, null, null, null, null);
    UserRequestData userRequestData = new UserRequestData(
        user.getEmail(),
        user.getPassword(),
        user.getName(),
        user.getLastname(),
        user.getPhoneNumber(),
        user.getGender()
        );

    String validationMessage = "Invalid field password. The password cannot be null.";
    when(userValidation.validate(any(User.class))).thenReturn(validationMessage);

    // when
    interactor.create(userRequestData);

    // then
    verify(userValidation, times(1)).validate(any(User.class));
    verify(userDataSource, times(0)).save(any(User.class));
    verify(userPreseter, times(1)).prepareFailView(validationMessage);
  }
}
