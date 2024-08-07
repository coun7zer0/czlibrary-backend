package cz.library.store.user.application;

import cz.library.store.user.domain.User;

public interface UserDataSourceGateway {
  
  boolean existsByEmail(String email);
  
  boolean existsByPhoneNumber(String phoneNumber);

  User save (User user);
}
