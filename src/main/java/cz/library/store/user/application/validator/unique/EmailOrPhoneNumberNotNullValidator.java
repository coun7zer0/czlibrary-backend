package cz.library.store.user.application.validator.unique;

import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class EmailOrPhoneNumberNotNullValidator extends UserValidator {

	@Override
	public String validate(User user) {
    if (user.getEmail() == null && user.getPhoneNumber() == null) {
      return "You must provide either email or phone number";
    }

    return validateNext(user);
	}
  
}
