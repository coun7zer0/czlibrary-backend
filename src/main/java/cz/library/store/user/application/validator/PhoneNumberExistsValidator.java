package cz.library.store.user.application.validator;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.UserValidator;
import cz.library.store.user.domain.User;

public class PhoneNumberExistsValidator extends UserValidator {

  private UserDataSourceGateway userDataSource;

  public PhoneNumberExistsValidator(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }
  
	public String validate(User user) {
    if (userDataSource.existsByPhoneNumber(user.getPhoneNumber())) {
      return "Invalid field phoneNumber. The phone number already exists in the daatbase";
    }

    return validateNext(user);
	}
  
}
