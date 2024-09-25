package cz.library.store.security.application.usecase.auth;

import java.util.Optional;

import cz.library.store.user.domain.User;

public interface AuthDataSourceGateway {

  Optional<User> findByEmail(String email);

  Optional<User> findByPhoneNumber(String phoneNumber);

}
