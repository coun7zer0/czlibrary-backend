package cz.library.store.security.application.usecase.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;
import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.user.domain.User;

public class LoginInteractorTest {

  @Mock
  private LoginPresenter loginPresenter;

  @Mock
  private LoginValidation loginValidation;

  @Mock
  private LoginDataSourceGateway userDataSource;

  @Mock
  private TokenProvider tokenProvider;

  @InjectMocks
  private LoginInteractor loginInteractor;

  @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void givenValidTokenData_whenLogin_thenReturnsSuccessView() {
    // given
    TokenRequestData tokenData = new TokenRequestData("jhon.doe@example.com", "validPassword123!");
    User user = new User();
    user.setEmail("jhon.doe@example.com");
    user.setPassword("validPassword123!");
    String generatedToken = "testToken";

    when(loginValidation.validate(tokenData)).thenReturn(null);
    when(userDataSource.findByUsername(tokenData.username())).thenReturn(user);
    when(tokenProvider.generateToken(user, tokenData.username())).thenReturn(generatedToken);

    TokenResponseData expectedResponse = new TokenResponseData(generatedToken);
    when(loginPresenter.prepareSuccessView(any(TokenResponseData.class)))
        .thenReturn(expectedResponse);

    // when
    TokenResponseData actualResponse = loginInteractor.login(tokenData);

    // then
    assertNotNull(actualResponse);
    assertEquals(expectedResponse.token(), actualResponse.token());
    verify(loginPresenter).prepareSuccessView(expectedResponse);
  }

  @Test
  void givenNullTokenData_whenLogin_thenReturnsFailView() {
    // given
    TokenRequestData tokenData = null;

    // when
    loginInteractor.login(tokenData);

    // then
    verify(loginPresenter, times(1)).prepareFailView("Bad request. No data received.");
    verify(loginPresenter, times(0)).prepareSuccessView(any(TokenResponseData.class));
    verify(tokenProvider, times(0)).generateToken(any(User.class), anyString());
  }

  @Test
  void givenInvalidTokenData_whenLogin_thenReturnsFailViewWithValidationError() {
    // given
    TokenRequestData tokenData = new TokenRequestData("invalidUsername", "invalidPassword");
    String validationMessage = "Invalid data";

    when(loginValidation.validate(tokenData)).thenReturn(validationMessage);

    // when
    loginInteractor.login(tokenData);

    // then
    verify(loginPresenter, times(1)).prepareFailView(validationMessage);
    verify(loginPresenter, times(0)).prepareSuccessView(any(TokenResponseData.class));
    verify(tokenProvider, times(0)).generateToken(any(User.class), anyString());
  }
}
