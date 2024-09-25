package cz.library.store.security.infrastructure.presenter;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cz.library.store.security.application.dto.AuthData;
import cz.library.store.security.application.presenter.AuthPresenter;

@Component
public class AuthFormatter implements AuthPresenter {

  @Override
  public AuthData prepareSuccessView(AuthData userDetails) {
    return userDetails;
  }

  @Override
  public AuthData prepareFailView(String Message) {
    throw new UsernameNotFoundException(Message);
  }
}
