package cz.library.store.user.application.validator;

import cz.library.store.user.application.UserValidator;
import cz.library.store.user.domain.User;

public class EmailOrPhoneNumberValidator extends UserValidator {

  
	public String validate(User user) {
    if(user.getEmail() == null && user.getPhoneNumber() == null) {
      return "You must provide at least the email field or the phoneNumber field.";
    }
    return validateNext(user);
	}
  
}
