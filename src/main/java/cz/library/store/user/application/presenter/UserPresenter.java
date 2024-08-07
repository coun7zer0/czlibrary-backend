package cz.library.store.user.application.presenter;

import cz.library.store.user.application.dto.UserResponseData;

public interface UserPresenter {
  UserResponseData prepareSuccessView(UserResponseData user);

  UserResponseData prepareFailView(String message);

  UserResponseData prepareFailView();
}
