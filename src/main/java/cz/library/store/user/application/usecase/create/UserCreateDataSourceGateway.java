package cz.library.store.user.application.usecase.create;

import cz.library.store.user.domain.User;

public interface UserCreateDataSourceGateway {

  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phoneNumber);

  User save (User user);
}
