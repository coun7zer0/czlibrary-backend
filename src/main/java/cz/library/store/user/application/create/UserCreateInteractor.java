package cz.library.store.user.application.create;

import cz.library.store.user.application.dto.UserRequestData;
import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.presenter.UserPresenter;
import cz.library.store.user.domain.User;

public class UserCreateInteractor implements UserCreateBoundary {

  private final UserPresenter userPresenter;
  private final UserCreateValidation userValidation;

  public UserCreateInteractor(UserPresenter userPresenter, UserCreateValidation userValidation) {
    this.userPresenter = userPresenter;
    this.userValidation = userValidation;
  }

	@Override
	public UserResponseData create(final UserRequestData data) {
    User user = data.toUser();
    String message = userValidation.validate(user);
    if ( message != null) {
      return userPresenter.prepareFailView(message);
    }
    return userPresenter.prepareSuccessView(new UserResponseData(user));
	}
  
}
