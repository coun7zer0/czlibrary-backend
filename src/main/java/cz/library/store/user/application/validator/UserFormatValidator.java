package cz.library.store.user.application.validator;

import cz.library.store.user.application.UserValidator;
import cz.library.store.user.domain.User;

public class UserFormatValidator extends UserValidator{

	@Override
	public String validate(User user) {
    if (user.getEmail() != null && !user.emailIsValid()) {
    	return "Invalid field email. The email format does not comply with RFC 2822.";
    }
    if (user.getPassword() != null && !user.passwordIsValid()) {
    	return "Invalid field password. The password must have at least 8 characters, "
          + "1 digit, one uppercase letter, one lowercase letter and one special character.";
    }
    if (user.getName() != null && !user.nameIsValid()) {
      return "Invalid field name. The name should contain only letters "
          + "and optionally one second name.";
    }
    if (user.getLastname() != null && !user.lastnameIsValid()) {
    	return "Invalid field lastname. The lastname should contain only "
          +"letters and optionally one second lastname.";
    }
    if (user.getPhoneNumber() != null && !user.phoneNumberIsValid()) {
    	return "Invalid field phoneNumber. The phone number can have minimum 7 digits, "
          + "maximum 15 digits and it must have start with \"+\".";
    }
    return validateNext(user);
	}
  
}
