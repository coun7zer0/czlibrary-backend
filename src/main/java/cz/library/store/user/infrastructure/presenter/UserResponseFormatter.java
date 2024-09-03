package cz.library.store.user.infrastructure.presenter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import cz.library.store.user.application.dto.UserResponseData;
import cz.library.store.user.application.presenter.UserPresenter;

@Component
public class UserResponseFormatter implements UserPresenter{

  @Override
  public UserResponseData prepareSuccessView(UserResponseData user) {
    return user;
  }

  @Override
  public UserResponseData prepareFailView(String message) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
  }

  @Override
  public UserResponseData prepareFailView() {
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

}
