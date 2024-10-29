package cz.library.store.security.infrastructure.usecase.login;

import org.springframework.stereotype.Component;

import cz.library.store.security.application.usecase.login.LoginDataSourceGateway;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;

@Component
public class JpaLogin implements LoginDataSourceGateway {

  private final JpaUserRepository userRepository;

  public JpaLogin(JpaUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByEmail(username)
        .map(user ->
            user.toUser())
        .orElseGet(() ->
            userRepository.findByPhoneNumber(username)
                .map(user -> user.toUser())
                .orElse(null));
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByEmail(username)
        || userRepository.existsByPhoneNumber(username);
  }

}
