package cz.library.store.user.application.validator;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.UserValidator;
import cz.library.store.user.domain.User;

public class EmailExistsValidator extends UserValidator {

  private UserDataSourceGateway userDataSource;

  public EmailExistsValidator(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }

	@Override
	public String validate(User user) {
    if (userDataSource.existsByEmail(user.getEmail())) {
      return "Invalid field email. The email already exists in the database.";
    }
    return validateNext(user);
	}
  
}
