package cz.library.store.user.infrastructure.usecase.create;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import cz.library.store.user.application.usecase.create.UserCreateDataSourceGateway;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

@Component
public class JpaUserCreate implements UserCreateDataSourceGateway {

  private final JpaUserRepository repository;

  public JpaUserCreate(JpaUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User save(User user) {
    String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
    user.setPassword(encodedPassword);
    return repository.save(new UserDataMapper(user)).toUser();
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public boolean existsByPhoneNumber(String phoneNumber) {
    return repository.existsByPhoneNumber(phoneNumber);
  }

}
