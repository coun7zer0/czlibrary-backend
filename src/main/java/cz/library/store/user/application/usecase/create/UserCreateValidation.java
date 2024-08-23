package cz.library.store.user.application.usecase.create;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.library.store.user.application.UserDataSourceGateway;
import cz.library.store.user.application.validation.UserFormat;
import cz.library.store.user.domain.User;
import cz.library.store.validation.application.FieldIsUnique;
import cz.library.store.validation.application.FieldNotNull;
import cz.library.store.validation.application.FieldNotNullOr;
import cz.library.store.validation.domain.Validator;

public class UserCreateValidation {

  private final UserDataSourceGateway userDataSource;

  public UserCreateValidation(UserDataSourceGateway userDataSource) {
    this.userDataSource = userDataSource;
  }

  public String validate(User user) {
    List<Validator<User>> validations = new ArrayList<>();

    validations.add(
        new FieldNotNull<>(
            "password", User::getPassword));

    validations.add(
        new FieldNotNullOr<>(new HashMap<>(Map.of(
            "email", User::getEmail,
            "phoneNumber", User::getPhoneNumber))));

    validations.add(
        new UserFormat());

    validations.add(
        new FieldIsUnique<>(
            "email", User::getEmail, userDataSource::existsByEmail));

    validations.add(
        new FieldIsUnique<>(
            "phoneNumber", User::getPhoneNumber, userDataSource::existsByPhoneNumber));

    Validator<User> validatorChain = Validator.link(validations);

    return validatorChain.validate(user);
  }

}
