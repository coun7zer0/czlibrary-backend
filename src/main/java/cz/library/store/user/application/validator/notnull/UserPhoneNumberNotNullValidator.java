package cz.library.store.user.application.validator.notnull;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class UserPhoneNumberNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getPhoneNumber() == null) {
      return "Invalid field phoneNumber. The phone number cannot be null.";
    }

    return validateNext(user);
	}
  
}
