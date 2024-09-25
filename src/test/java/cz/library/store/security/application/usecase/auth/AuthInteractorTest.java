package cz.library.store.security.application.usecase.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.security.application.presenter.AuthPresenter;
import cz.library.store.user.domain.User;

public class AuthInteractorTest {

  @Mock
  private AuthDataSourceGateway authDataSource;

  @Mock
  private AuthPresenter authPresenter;

  private AuthInteractor interactor;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.interactor = new AuthInteractor(authDataSource, authPresenter);
  }

  @Test
  public void givenExistingEmail_whenAuth_thenPrepareSuccessView() {
    // given
    String identifier = "jhon.doe@example.com";
    User existingUser = new User();

    when(authDataSource.findByEmail(identifier)).thenReturn(Optional.ofNullable(existingUser));
    when(authDataSource.findByPhoneNumber(identifier)).thenReturn(Optional.ofNullable(null));

    // when
    interactor.auth(identifier);

    // then
    verify(authDataSource, times(1)).findByEmail(identifier);
    verify(authPresenter, times(1)).prepareSuccessView(any(AuthData.class));
  }

  @Test
  public void givenExistingPhoneNumber_whenAuth_thenPrepareSuccessView() {
    // given
    String identifier = "+420123456789";
    User existingUser = new User();

    when(authDataSource.findByEmail(identifier)).thenReturn(Optional.ofNullable(null));
    when(authDataSource.findByPhoneNumber(identifier)).thenReturn(Optional.ofNullable(existingUser));

    // when
    interactor.auth(identifier);

    // then
    verify(authDataSource, times(1)).findByPhoneNumber(identifier);
    verify(authPresenter, times(1)).prepareSuccessView(any(AuthData.class));
  }

  @Test
  public void givenUnexitingIdentifier_whenAuth_thenPrepareFailView() {
    // given
    String identifier = "jhon.doe@example.com";

    when(authDataSource.findByEmail(identifier)).thenReturn(Optional.ofNullable(null));
    when(authDataSource.findByPhoneNumber(identifier)).thenReturn(Optional.ofNullable(null));

    // when
    interactor.auth(identifier);

    // then
    verify(authDataSource, times(1)).findByEmail(identifier);
    verify(authDataSource, times(1)).findByPhoneNumber(identifier);
    verify(authPresenter, times(0)).prepareSuccessView(any(AuthData.class));
    verify(authPresenter, times(1)).prepareFailView("User not found with identifier: jhon.doe@example.com");
  }

}
