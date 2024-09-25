package cz.library.store.security.infrastructure.usecase.auth;

import java.util.Optional;

import org.springframework.stereotype.Component;

import cz.library.store.security.application.usecase.auth.AuthDataSourceGateway;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;

@Component
public class JpaAuth implements AuthDataSourceGateway{

  public final JpaUserRepository repository;

  public JpaAuth(JpaUserRepository repository) {
    this.repository = repository;
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email).map(data -> data.toUser());
  }

  public Optional<User> findByPhoneNumber(String phoneNumber) {
    return repository.findByPhoneNumber(phoneNumber).map(data -> data.toUser());
  }

}
