package cz.library.store.security.application.usecase.login;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;
import cz.library.store.security.application.service.TokenProvider;
import cz.library.store.user.domain.User;

public class LoginInteractor implements LoginBoundary {

  private final LoginPresenter loginPresenter;

  private final LoginValidation loginValidation;

  private final LoginDataSourceGateway userDataSource;

  private final TokenProvider tokenProvider;

  public LoginInteractor(
      LoginPresenter loginPresenter,
      LoginValidation loginValidation,
      LoginDataSourceGateway userDataSource,
      TokenProvider tokenProvider) {
    this.loginPresenter = loginPresenter;
    this.loginValidation = loginValidation;
    this.userDataSource = userDataSource;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public TokenResponseData login(TokenRequestData tokenData) {
    if (tokenData == null) {
      return loginPresenter.prepareFailView("Bad request. No data received.");
    }

    String message = loginValidation.validate(tokenData);

    if (message != null) {
      return loginPresenter.prepareFailView(message);
    }

    User user = userDataSource.findByUsername(tokenData.username());
    String token = tokenProvider.generateToken(user, tokenData.username());

    return loginPresenter.prepareSuccessView(new TokenResponseData(token));
  }

}
