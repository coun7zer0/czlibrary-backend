package cz.library.store.user.application.validator.unique;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class PhoneNumberNotExistsValidator extends UserValidator {
  
  private UserDataSourceGateway userDataSource;

  public PhoneNumberNotExistsValidator(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }
  
	public String validate(User user) {
    if (userDataSource.existsByPhoneNumber(user.getPhoneNumber())) {
      return "Invalid field phoneNumber. The phone number already exists in the daatbase";
    }

    return validateNext(user);
	}
  
}
