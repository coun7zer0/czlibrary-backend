package cz.library.store.security.application.usecase.login.validation;

import java.util.function.BiPredicate;
import java.util.function.Function;

import cz.library.store.security.application.dto.TokenRequestData;
import cz.library.store.user.domain.User;
import cz.library.store.validation.domain.Validator;

public class CredentialsAreValid extends Validator<TokenRequestData> {

  private final Function<String, User> userGetterByUsername;

  private final BiPredicate<String, String> matcher;

  public CredentialsAreValid(
      Function<String, User> userGetterByUsername,
      BiPredicate<String, String> matcher) {
    this.userGetterByUsername = userGetterByUsername;
    this.matcher = matcher;
  }

  @Override
  public String validate(TokenRequestData entity) {
    User user = userGetterByUsername.apply(entity.username());
    if (matcher.negate().test(entity.password(), user.getPassword())) {
      return "Wrong password";
    }
    return validateNext(entity);
  }

}
