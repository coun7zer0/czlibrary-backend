package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserEmailNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getEmail() == null) {
      return "Invalid field email. The Email cannot be null.";
    }

    return validateNext(user);
	}
  
}
