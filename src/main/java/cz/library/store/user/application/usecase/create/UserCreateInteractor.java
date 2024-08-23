package cz.library.store.user.application.usecase.create;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.presenter.UserPresenter;
import cz.library.store.user.domain.User;

public class UserCreateInteractor implements UserCreateBoundary {

  private final UserPresenter userPresenter;
  private final UserCreateValidation userValidation;
  private final UserDataSourceGateway userDataSource;

  public UserCreateInteractor(
      UserPresenter userPresenter,
      UserCreateValidation userValidation,
      UserDataSourceGateway userDataSource) {
    this.userPresenter = userPresenter;
    this.userValidation = userValidation;
    this.userDataSource = userDataSource;
  }

  @Override
  public UserResponseData create(final UserRequestData data) {
    if (data == null) {
      return userPresenter.prepareFailView("Missing data");
    }

    User user = data.toUser();
    String message = userValidation.validate(user);
    if ( message != null) {
      return userPresenter.prepareFailView(message);
    }

    User savedUser = userDataSource.save(user);

    return userPresenter.prepareSuccessView(new UserResponseData(savedUser));
  }

}
