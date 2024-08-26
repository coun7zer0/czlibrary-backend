package cz.library.store.user.application.validation;

import cz.library.store.user.domain.User;
import cz.library.store.validation.application.ValidatorBuilder;

public class UserValidatorBuilder extends ValidatorBuilder<User, UserValidatorBuilder> {

  public UserValidatorBuilder userFormat() {
    validatorChain.add(new UserFormat());
    return this;
  }

}
