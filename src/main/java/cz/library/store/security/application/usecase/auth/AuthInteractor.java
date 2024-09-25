package cz.library.store.security.application.usecase.auth;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.security.application.presenter.AuthPresenter;
import cz.library.store.user.domain.User;

public class AuthInteractor implements AuthBoundary {

  public final AuthDataSourceGateway authDataSource;
  public final AuthPresenter authPresenter;

  public AuthInteractor(AuthDataSourceGateway authDataSource ,AuthPresenter authPresenter) {
    this.authDataSource = authDataSource;
    this.authPresenter = authPresenter;
  }

  @Override
  public AuthData auth(String identifier) {
    return authDataSource.findByEmail(identifier)
        .map(user -> authPresenter
            .prepareSuccessView(new AuthData(user, User::getEmail)))
        .orElseGet(() -> authDataSource
            .findByPhoneNumber(identifier)
            .map(user -> authPresenter
                .prepareSuccessView(new AuthData(user, User::getPhoneNumber)))
            .orElseGet(() -> authPresenter
                .prepareFailView("User not found with identifier: " + identifier)));
  }

}
