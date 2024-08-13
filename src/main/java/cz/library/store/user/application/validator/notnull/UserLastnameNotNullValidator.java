package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserLastnameNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getLastname() == null) {
      return "Invalid field lastname. The lastname cannot be null.";
    }

    return validateNext(user);
	}
  
}
