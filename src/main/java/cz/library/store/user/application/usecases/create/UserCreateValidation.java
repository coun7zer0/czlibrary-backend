package cz.library.store.user.application.usecases.create;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.validator.UserValidator;
import cz.library.store.user.application.validator.notnull.UserPasswordNotNullValidator;
import cz.library.store.user.application.validator.unique.EmailNotExistsValidator;
import cz.library.store.user.application.validator.unique.EmailOrPhoneNumberNotNullValidator;
import cz.library.store.user.application.validator.unique.PhoneNumberNotExistsValidator;
import cz.library.store.user.application.validator.unique.UserFormatValidator;
import cz.library.store.user.domain.User;

public class UserCreateValidation {

  private UserDataSourceGateway userDataSource;

  public UserCreateValidation(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }

  public String validate(User user) {
    UserValidator userValidator = UserValidator.link(
        new EmailOrPhoneNumberNotNullValidator(),
        new UserPasswordNotNullValidator(),
        new UserFormatValidator(),
        new EmailNotExistsValidator(userDataSource),
        new PhoneNumberNotExistsValidator(userDataSource));

    return userValidator.validate(user);
  }
  
}
