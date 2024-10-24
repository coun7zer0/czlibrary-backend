package cz.library.store.security.application.usecase.login;

import java.util.Map;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.security.application.service.PasswordEncoder;
import cz.library.store.security.application.usecase.login.validation.LoginValidatorBuilder;

public class LoginValidation {

  private final LoginDataSourceGateway userDataSource;
  private final PasswordEncoder passwordEncoder;

  public LoginValidation(LoginDataSourceGateway userDataSource, PasswordEncoder passwordEncoder) {
    this.userDataSource = userDataSource;
    this.passwordEncoder = passwordEncoder;
  }

  public String validate(TokenRequestData tokenData) {
    return new LoginValidatorBuilder()
        .fieldNotNullAnd(Map.of(
            "username", TokenRequestData::username,
            "password", TokenRequestData::password))
        .fieldExists(
            "username", TokenRequestData::username,
            userDataSource::existsByUsername)
        .credentialsAreValid(
            userDataSource::findByUsername,
            passwordEncoder::matches)
        .build()
        .validate(tokenData);
  }

}
