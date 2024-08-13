package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserGenderNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getGender() == null) {
      return "Invalid field gender. The gender cannot be null.";
    }

    return validateNext(user);
	}
  
}
