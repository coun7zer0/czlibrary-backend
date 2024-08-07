package cz.library.store.user.application.create;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.UserValidator;
import cz.library.store.user.application.validator.EmailExistsValidator;
import cz.library.store.user.application.validator.EmailOrPhoneNumberValidator;
import cz.library.store.user.application.validator.PhoneNumberExistsValidator;
import cz.library.store.user.application.validator.UserFormatValidator;
import cz.library.store.user.domain.User;

public class UserCreateValidation {

  private UserDataSourceGateway userDataSource;

  public UserCreateValidation(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }

  public String validate(User user) {
    UserValidator userValidator = UserValidator.link(
        new EmailOrPhoneNumberValidator(),
        new UserFormatValidator(),
        new EmailExistsValidator(userDataSource),
        new PhoneNumberExistsValidator(userDataSource));

    return userValidator.validate(user);
  }
  
}
