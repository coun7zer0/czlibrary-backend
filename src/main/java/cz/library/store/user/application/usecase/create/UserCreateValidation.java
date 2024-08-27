package cz.library.store.user.application.usecase.create;

import java.util.Map;

import cz.library.store.user.application.validation.UserValidatorBuilder;
import cz.library.store.user.domain.User;

public class UserCreateValidation {

  private final UserCreateDataSourceGateway userDataSource;

  public UserCreateValidation(UserCreateDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }

  public String validate(User user) {
    return new UserValidatorBuilder()
        .fieldNotNull(
            "password", User::getPassword)
        .fieldNotNullOr(Map.of(
            "email", User::getEmail,
            "phoneNumber", User::getPhoneNumber))
        .userFormat()
        .fieldIsUnique(
            "email", User::getEmail, userDataSource::existsByEmail)
        .fieldIsUnique(
            "phoneNumber", User::getPhoneNumber, userDataSource::existsByPhoneNumber)
        .build()
        .validate(user);
  }

}
