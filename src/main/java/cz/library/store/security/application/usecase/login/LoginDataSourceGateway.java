package cz.library.store.security.application.usecase.login;

import cz.library.store.user.domain.User;

public interface LoginDataSourceGateway {

  User findByUsername(String username);

  boolean existsByUsername(String username);

}
