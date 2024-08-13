package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserNameNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getName() == null) {
      return "Invalid field name. The name cannot be null.";
    }

    return validateNext(user);
	}
  
}
