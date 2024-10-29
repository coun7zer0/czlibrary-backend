package cz.library.store.security.infrastructure.usecase.login;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import cz.library.store.security.application.dto.TokenResponseData;
import cz.library.store.security.application.usecase.login.LoginPresenter;

@Component
public class LoginResponseFormatter implements LoginPresenter {

  @Override
  public TokenResponseData prepareSuccessView(TokenResponseData tokenResponseData) {
    return tokenResponseData;
  }

  @Override
  public TokenResponseData prepareFailView(String message) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
  }

}
