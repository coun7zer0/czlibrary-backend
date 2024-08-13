package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserIdNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getId() == null) {
      return "Invalid field id. The id cannot be null.";
    }
    return validateNext(user);
	}
  
}
