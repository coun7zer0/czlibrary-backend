package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserPasswordNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getPassword() == null) {
      return "Invalid field password. The password cannot be null.";
    }

    return validateNext(user);
	}
  
}
