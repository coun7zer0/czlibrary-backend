package cz.library.store.security.infrastructure.usecase.login;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;
import cz.library.store.security.application.usecase.login.LoginBoundary;

@Service
public class LoginService {

  private final LoginBoundary loginInteractor;

  public LoginService(LoginBoundary loginInteractor) {
    this.loginInteractor = loginInteractor;
  }

  public ResponseEntity<TokenResponseData> login(TokenRequestData tokenData) {
    return ResponseEntity.ok(loginInteractor.login(tokenData));
  }

}
