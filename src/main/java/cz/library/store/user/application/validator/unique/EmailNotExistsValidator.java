package cz.library.store.user.application.validator.unique;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.domain.User;

public class EmailNotExistsValidator extends UserValidator {

  private UserDataSourceGateway userDataSource;
  
  public EmailNotExistsValidator(UserDataSourceGateway userDataSource) {
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
