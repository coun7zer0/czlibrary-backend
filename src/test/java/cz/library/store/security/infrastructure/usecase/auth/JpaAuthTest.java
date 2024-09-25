package cz.library.store.security.infrastructure.usecase.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cz.library.store.user.domain.User;
import cz.library.store.user.infrastructure.persistence.JpaUserRepository;
import cz.library.store.user.infrastructure.persistence.UserDataMapper;

public class JpaAuthTest {

  @Mock
  private JpaUserRepository userRepository;

  private JpaAuth authDataSource;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.authDataSource = new JpaAuth(userRepository);
  }

  @Test
  public void givenExistingEmail_whenFindByEmail_thenReturnsOptionalWithUser() {
    // given
    String email = "jhon.doe@example.com";
    User user = new User();
    user.setEmail(email);
    user.setId(1L);

    UserDataMapper userData = new UserDataMapper(user);
    when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(userData));

    // when
    User existingUser = authDataSource.findByEmail(email).orElse(null);

    // then
    assertEquals(user.getEmail(), existingUser.getEmail());
    assertEquals(user.getId(), existingUser.getId());
  }

  @Test
  public void givenUnexistingEmail_whenFindByEmail_thenReturnsVoidOptional() {
    // given
    String email = "jhon.doe@example.com";

    when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(null));

    // when
    User unexistingUser = authDataSource.findByEmail(email).orElse(null);

    // then
    assertEquals(null, unexistingUser);
  }

  @Test
  public void givenExistingPhoneNumber_whenFindByPhoneNumber_thenReturnsOptionalWithUser() {
    // given
    String phoneNumber = "+420123456789";
    User user = new User();
    user.setPhoneNumber(phoneNumber);
    user.setId(1L);

    UserDataMapper userData = new UserDataMapper(user);
    when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.ofNullable(userData));

    // when
    User existingUser = authDataSource.findByPhoneNumber(phoneNumber).orElse(null);

    // then
    assertEquals(user.getPhoneNumber(), existingUser.getPhoneNumber());
    assertEquals(user.getId(), existingUser.getId());
  }

  @Test
  public void givenUnexistingPhoneNumber_whenFindByPhoneNumber_thenReturnsVoidOptional() {
    // given
    String phoneNumber = "+420123456789";

    when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.ofNullable(null));

    // when
    User unexistingUser = authDataSource.findByPhoneNumber(phoneNumber).orElse(null);

    // then
    assertEquals(null, unexistingUser);
  }
}
