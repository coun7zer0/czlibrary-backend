package cz.library.store.security.application.usecase.login.validation;

import java.util.function.BiPredicate;
import java.util.function.Function;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.user.domain.User;
import cz.library.store.validation.application.ValidatorBuilder;

public class LoginValidatorBuilder extends ValidatorBuilder<TokenRequestData, LoginValidatorBuilder> {

  public LoginValidatorBuilder credentialsAreValid(
      Function<String, User> userGetterByUsername,
      BiPredicate<String, String> matcher) {
    validatorChain.add(new CredentialsAreValid(userGetterByUsername, matcher));
    return this;
  }
}
