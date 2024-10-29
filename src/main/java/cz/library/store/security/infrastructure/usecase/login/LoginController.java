package cz.library.store.security.infrastructure.usecase.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.dto.TokenResponseData;

@RestController
public class LoginController {

  public final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponseData> login(@RequestBody TokenRequestData tokenData) {
    return loginService.login(tokenData);
  }

}
