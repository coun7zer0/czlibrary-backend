package cz.library.store.user.infrastructure.usecase.create;

import org.springframework.stereotype.Component;

import cz.library.store.security.application.service.PasswordEncoder;
import cz.library.store.user.application.usecase.create.UserCreateDataSourceGateway;
import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

@Component
public class JpaUserCreate implements UserCreateDataSourceGateway {

  private final JpaUserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public JpaUserCreate(JpaUserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User save(User user) {
    String encodedPassword = passwordEncoder.encode(user.getPassword());
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
