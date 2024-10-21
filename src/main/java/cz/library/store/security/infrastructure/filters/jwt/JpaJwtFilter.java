package cz.library.store.security.infrastructure.filters.jwt;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import cz.library.store.security.infrastructure.model.AuthDetails;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;

@Component
public class JpaJwtFilter {

  private final JpaUserRepository repository;

  public JpaJwtFilter(JpaUserRepository repository) {
    this.repository = repository;
  }

  public Optional<AuthDetails> findById(Long id, String username) {
    return repository.findById(id)
        .filter(user -> username.equals(user.getEmail()) || username.equals(user.getPhoneNumber()))
        .map(user -> {
          Function<User, String> usernameGetter = User::getEmail;
          if (username.equals(user.getPhoneNumber())) {
            usernameGetter = User::getPhoneNumber;
          }
          return new AuthDetails(user.toUser(), usernameGetter);
        });

  }
}
